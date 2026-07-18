package com.dad.sales_api.order.service;

import com.dad.sales_api.order.dto.input.AddItemsInputDTO;
import com.dad.sales_api.order.dto.input.FindManyOrdersInputDTO;
import com.dad.sales_api.order.dto.output.AddItemsOutputDTO;
import com.dad.sales_api.order.dto.output.FindManyOrdersOutputDTO;
import com.dad.sales_api.shared.enums.OrderStatusEnum;
import com.dad.sales_api.shared.exceptions.NotFoundException;
import com.dad.sales_api.shared.helpers.PaginationHelper;
import com.dad.sales_api.shared.mappers.UserOrderMapper;
import com.dad.sales_api.shared.persistence.postgres.dto.UserOrderSimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.entities.FigureEntity;
import com.dad.sales_api.shared.persistence.postgres.entities.UserEntity;
import com.dad.sales_api.shared.persistence.postgres.entities.UserOrderEntity;
import com.dad.sales_api.shared.persistence.postgres.entities.UserOrderFigureEntity;
import com.dad.sales_api.shared.persistence.postgres.entities.custom_id.UserOrderFigureId;
import com.dad.sales_api.shared.persistence.postgres.repositories.FigureRepository;
import com.dad.sales_api.shared.persistence.postgres.repositories.UserOrderFigureRepository;
import com.dad.sales_api.shared.persistence.postgres.repositories.UserOrderRepository;
import com.dad.sales_api.shared.persistence.postgres.repositories.UserRepository;
import com.dad.sales_api.shared.persistence.postgres.specifications.UserOrderSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
  private final UserOrderRepository userOrderRepository;
  private final UserOrderFigureRepository userOrderFigureRepository;
  private final FigureRepository figureRepository;
  private final UserRepository userRepository;

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

  public AddItemsOutputDTO addItem(AddItemsInputDTO input){
    FigureEntity figure = figureRepository.findById(input.figureId()).orElseThrow(
        () -> new NotFoundException("{exception.figure.not-found}")
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

    order = recalculateOrderPrice(order, figure.getPrice(), input.quantity());

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

  private UserOrderEntity createOrder(Integer userId) {
    UserEntity user = userRepository.findById(userId)
        .orElseThrow(() -> new NotFoundException("{exception.user.not-found}"));

    UserOrderEntity order = new UserOrderEntity();
    order.setUser(user);
    order.setStatus(OrderStatusEnum.IN_PROGRESS);
    order.setPrice(BigDecimal.ZERO);
    order.setDiscount(BigDecimal.ZERO);
    order.setFinalPrice(BigDecimal.ZERO);
    order.setCreatedAt(LocalDateTime.now());
    order.setInstallmentsCount(1);

    return userOrderRepository.save(order);
  }

  private UserOrderEntity recalculateOrderPrice(UserOrderEntity order, BigDecimal figurePrice, Integer quantity) {
    BigDecimal total = order.getPrice().add(figurePrice.multiply(BigDecimal.valueOf(quantity)));

    order.setPrice(total);

    BigDecimal discount = BigDecimal.ZERO;
    order.setDiscount(discount);

    order.setFinalPrice(total.subtract(discount));

    return userOrderRepository.save(order);
  }
}
