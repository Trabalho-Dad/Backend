package com.dad.sales_api.order.service;

import com.dad.sales_api.order.dto.input.*;
import com.dad.sales_api.order.dto.output.*;
import com.dad.sales_api.shared.SalesConstants;
import com.dad.sales_api.shared.enums.OrderStatusEnum;
import com.dad.sales_api.shared.enums.PaymentStatusEnum;
import com.dad.sales_api.shared.exceptions.BadRequestException;
import com.dad.sales_api.shared.exceptions.ConflictException;
import com.dad.sales_api.shared.exceptions.NotFoundException;
import com.dad.sales_api.shared.helpers.PaginationHelper;
import com.dad.sales_api.shared.helpers.services.MessageService;
import com.dad.sales_api.shared.mappers.AddressMapper;
import com.dad.sales_api.shared.mappers.PaymentMapper;
import com.dad.sales_api.shared.mappers.UserOrderFigureMapper;
import com.dad.sales_api.shared.mappers.UserOrderMapper;
import com.dad.sales_api.shared.persistence.postgres.dto.UserOrderSimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.entities.*;
import com.dad.sales_api.shared.persistence.postgres.entities.custom_id.UserOrderFigureId;
import com.dad.sales_api.shared.persistence.postgres.repositories.*;
import com.dad.sales_api.shared.persistence.postgres.specifications.CouponSpecification;
import com.dad.sales_api.shared.persistence.postgres.specifications.UserOrderSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
  private final UserOrderRepository userOrderRepository;
  private final UserOrderFigureRepository userOrderFigureRepository;
  private final FigureRepository figureRepository;
  private final UserRepository userRepository;
  private final AddressRepository addressRepository;
  private final PaymentRepository paymentRepository;
  private final CouponRepository couponRepository;
  private final MessageService messageService;

  public FindManyOrdersOutputDTO findMyOrders(FindManyOrdersInputDTO input){
    Specification<UserOrderEntity> spec = Specification
        .where(UserOrderSpecification.withUserId(input.userId()))
        .and(UserOrderSpecification.withStatus(input.status()));

    int count = (int) userOrderRepository.count(spec);

    int totalPages = (int) Math.ceil((double) count / input.take());

    List<UserOrderEntity> userOrders = userOrderRepository.findAll(
        spec,
        PaginationHelper.calculatePage(
            input.page(),
            totalPages,
            input.take()
        )
    ).getContent();

    List<UserOrderSimpleDTO> output = userOrders
        .stream()
        .map(u -> UserOrderMapper.convertEntityToSimpleDTO(u))
        .toList();

    return new FindManyOrdersOutputDTO(
        output,
        totalPages,
        count
    );
  }

  @Transactional
  public FindOrderByIdOutputDTO findById(FindOrderByIdInputDTO input){
    UserOrderEntity order = userOrderRepository.findByIdAndUserId(
        input.id(),
        input.userId()
    ).orElseThrow(
        () -> new NotFoundException(
            messageService.getMessage("exception.order.not-found")
        )
    );

    return new FindOrderByIdOutputDTO(
        order.getId(),
        order.getPrice(),
        order.getDiscount(),
        order.getFinalPrice(),
        order.getStatus(),
        order.getCreatedAt(),
        order.getFigures().stream().map(UserOrderFigureMapper::convertEntityToSimpleDTO).toList(),
        order.getPayments().stream().map(PaymentMapper::convertEntityToSimpleDTO).toList()
    );
  }

  @Transactional
  public AddItemsOutputDTO addItem(AddItemsInputDTO input){
    FigureEntity figure = figureRepository.findById(input.figureId()).orElseThrow(
        () -> new NotFoundException(
            messageService.getMessage("exception.figure.not-found")
        )
    );

    if (input.quantity() > figure.getQuantity()) throw new ConflictException(
        messageService.getMessage("exception.figure-quantity.out-of-range")
    );

    UserOrderEntity order = userOrderRepository.findByUserIdAndStatus(
        input.userId(), OrderStatusEnum.IN_PROGRESS
    ).orElseGet(
        () -> createOrder(input.userId())
    );

    UserOrderFigureEntity item = null;

    if (order.getPrice().compareTo(BigDecimal.ZERO) > 0) {
       item = userOrderFigureRepository.findByUserOrderIdAndFigureId(order.getId(), input.figureId())
          .orElse(null);
    }

    if (item == null) {
      item = new UserOrderFigureEntity();
      item.setId(new UserOrderFigureId(order.getId(), figure.getId()));
      item.setUserOrder(order);
      item.setFigure(figure);
      item.setQuantity(input.quantity());
      item.setPrice(figure.getPrice().multiply(BigDecimal.valueOf(input.quantity())));
    } else {
      item.setQuantity(item.getQuantity() + input.quantity());
      item.setPrice(item.getPrice().add(figure.getPrice().multiply(BigDecimal.valueOf(input.quantity()))));
    }

    userOrderFigureRepository.save(item);

    order = recalculateMoreOrderPrice(order, figure.getPrice(), input.quantity());

    return new AddItemsOutputDTO(
        order.getId(),
        order.getPrice(),
        input.figureId(),
        figure.getName(),
        figure.getPrice(),
        input.quantity(),
        figure.getPrice().multiply(BigDecimal.valueOf(input.quantity()))
    );
  }

  @Transactional
  public RemoveItemOutputDTO removeItem(RemoveItemInputDTO input){
    FigureEntity figure = figureRepository.findById(input.figureId()).orElseThrow(
        () -> new NotFoundException(
            messageService.getMessage("exception.figure.not-found")
        )
    );

    UserOrderEntity order = userOrderRepository.findByUserIdAndStatus(
        input.userId(), OrderStatusEnum.IN_PROGRESS
    ).orElseThrow(
        () -> new NotFoundException(
            messageService.getMessage("exception.order.not-found")
        )
    );

    UserOrderFigureEntity item = userOrderFigureRepository.findByUserOrderIdAndFigureId(order.getId(), input.figureId())
        .orElseThrow(
            () -> new NotFoundException(
                messageService.getMessage("exception.order.item.not-found")
            )
        );

    if (input.quantity() == null || input.quantity() > item.getQuantity()) {
      userOrderFigureRepository.deleteById(item.getId());

      order = recalculateLessOrderPrice(order, figure.getPrice(), item.getQuantity());
    } else {
      item.setQuantity(item.getQuantity() - input.quantity());
      item.setPrice(figure.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));

      userOrderFigureRepository.save(item);

      order = recalculateLessOrderPrice(order, figure.getPrice(), input.quantity());
    }

    return new RemoveItemOutputDTO(
        order.getId(),
        order.getPrice(),
        input.figureId(),
        figure.getName()
    );
  }

  @Transactional
  public FinishOrderOutputDTO finishOrder(FinishOrderInputDTO input){
    AddressEntity address = addressRepository.findByIdAndUserId(input.addressId(), input.userId()).orElseThrow(
        () -> new NotFoundException(
            messageService.getMessage(
                "exception.address.not-found"
            )
        )
    );

    UserOrderEntity order = userOrderRepository.findByUserIdAndStatus(
        input.userId(), OrderStatusEnum.IN_PROGRESS
    ).orElseThrow(
        () -> new NotFoundException(
            messageService.getMessage("exception.finish.no-order")
        )
    );

    order.setAddress(address);
    order.setShippingCost(input.shippingCost());
    order.setEstimatedDeliveryTime(input.estimatedDeliveryTime());
    order.increaseOrderPrice(input.shippingCost());
    order.setInstallmentsCount(input.installmentsCount());
    order.setStatus(OrderStatusEnum.ORDERED);
    order.setPrice(order.getPrice().add(input.shippingCost()));

    userOrderRepository.save(order);

    BigDecimal installmentValue = order.getFinalPrice().divideToIntegralValue(BigDecimal.valueOf(input.installmentsCount()));

    for (int i = 0; i < input.installmentsCount(); i++){
      paymentRepository.save(
          new PaymentEntity(
              i + 1,
              installmentValue,
              LocalDate.now().plusMonths(i).plusDays(SalesConstants.DAYS_TO_PAY),
              input.paymentType(),
              PaymentStatusEnum.PENDING,
              order
          )
      );
    }

    return new FinishOrderOutputDTO(
        order.getId(),
        order.getPrice(),
        order.getShippingCost(),
        order.getDiscount(),
        order.getFinalPrice(),
        order.getEstimatedDeliveryTime(),
        order.getInstallmentsCount(),
        order.getStatus(),
        order.getCreatedAt(),
        AddressMapper.convertEntityToSimpleDTO(order.getAddress()),
        order.getFigures().stream().map(UserOrderFigureMapper::convertEntityToSimpleDTO).toList(),
        PaymentMapper.convertEntityToSimpleDTO(paymentRepository.findFirstByUserOrderId(order.getId()))
    );
  }

  @Transactional
  public AddCouponOutputDTO addCoupon(AddCouponInputDTO input){
    UserOrderEntity order = userOrderRepository.findByIdAndUserId(
        input.orderId(),
        input.userId()
    ).orElseThrow(
        () -> new NotFoundException(
            messageService.getMessage("exception.order.not-found")
        )
    );

    if (order.getStatus() != OrderStatusEnum.IN_PROGRESS) throw new ConflictException(
        messageService.getMessage("exception.coupon.order-status")
    );

    Specification<CouponEntity> spec = Specification
        .where(CouponSpecification.withCode(input.code()))
        .and(CouponSpecification.available(Boolean.TRUE))
        .and(CouponSpecification.validDate(LocalDate.now()));

    CouponEntity coupon = couponRepository.findOne(spec).orElseThrow(
        () -> new ConflictException(
            messageService.getMessage("exception.coupon.invalid")
        )
    );

    if (order.getCoupons().stream().anyMatch(c -> c.getId().equals(coupon.getId()))) {
      throw new BadRequestException(
          messageService.getMessage("exception.coupon.already-added")
      );
    }

    coupon.setUsageCount(coupon.getUsageCount() + 1);

    if (order.getCoupons() == null) {
      order.setCoupons(new ArrayList<>());
    }

    order.getCoupons().add(coupon);

    BigDecimal discount = calculateDiscount(order);

    order.setDiscount(discount);
    order.setFinalPrice(order.getPrice().subtract(discount));

    userOrderRepository.save(order);

    return new AddCouponOutputDTO(
        coupon.getId(),
        order.getId(),
        coupon.getDiscountPct(),
        order.getDiscount(),
        order.getPrice(),
        order.getFinalPrice()
    );
  }

  public void cancelOrder(CancelOrderInputDTO input){
    UserOrderEntity order = userOrderRepository.findByIdAndUserId(
        input.orderId(),
        input.userId()
    ).orElseThrow(
        () -> new NotFoundException(
            messageService.getMessage("exception.order.not-found")
        )
    );

    if (order.getStatus() == OrderStatusEnum.DELIVERED) throw new ConflictException(
        messageService.getMessage("exception.order.cancel")
    );

    order.setStatus(OrderStatusEnum.CANCELED);

    userOrderRepository.save(order);
  }

  private UserOrderEntity createOrder(Integer userId) {
    UserEntity user = userRepository.findById(userId)
        .orElseThrow(() -> new NotFoundException(
            messageService.getMessage("exception.user.not-found"))
        );

    UserOrderEntity order = new UserOrderEntity();
    order.setUser(user);
    order.setStatus(OrderStatusEnum.IN_PROGRESS);
    order.setPrice(BigDecimal.ZERO);
    order.setDiscount(BigDecimal.ZERO);
    order.setFinalPrice(BigDecimal.ZERO);
    order.setCreatedAt(LocalDateTime.now());
    order.setCoupons(new ArrayList<>());

    return userOrderRepository.save(order);
  }

  private UserOrderEntity recalculateMoreOrderPrice(UserOrderEntity order, BigDecimal figurePrice, Integer quantity) {

    BigDecimal total = order.getPrice()
        .add(figurePrice.multiply(BigDecimal.valueOf(quantity)));

    order.setPrice(total);

    BigDecimal discount = calculateDiscount(order);
    order.setDiscount(discount);

    order.setFinalPrice(total.subtract(discount));

    return userOrderRepository.save(order);
  }

  private UserOrderEntity recalculateLessOrderPrice(UserOrderEntity order, BigDecimal figurePrice, Integer quantity) {
    BigDecimal total = order.getPrice()
        .subtract(figurePrice.multiply(BigDecimal.valueOf(quantity)));

    order.setPrice(total);

    BigDecimal discount = calculateDiscount(order);
    order.setDiscount(discount);

    order.setFinalPrice(total.subtract(discount));

    return userOrderRepository.save(order);
  }

  private BigDecimal calculateDiscount(UserOrderEntity order) {
    BigDecimal totalDiscountPct = order.getCoupons().stream()
        .map(CouponEntity::getDiscountPct)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    return order.getPrice()
        .multiply(totalDiscountPct)
        .divide(BigDecimal.valueOf(100));
  }
}
