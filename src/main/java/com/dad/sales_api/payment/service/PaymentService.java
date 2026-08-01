package com.dad.sales_api.payment.service;

import com.dad.sales_api.payment.dto.input.FindManyPaymentsInputDTO;
import com.dad.sales_api.payment.dto.input.PayInputDTO;
import com.dad.sales_api.payment.dto.output.FindManyPaymentsOutputDTO;
import com.dad.sales_api.payment.dto.output.PayOutputDTO;
import com.dad.sales_api.shared.enums.OrderStatusEnum;
import com.dad.sales_api.shared.enums.PaymentStatusEnum;
import com.dad.sales_api.shared.exceptions.ConflictException;
import com.dad.sales_api.shared.exceptions.NotFoundException;
import com.dad.sales_api.shared.helpers.PaginationHelper;
import com.dad.sales_api.shared.helpers.services.MessageService;
import com.dad.sales_api.shared.mappers.PaymentMapper;
import com.dad.sales_api.shared.persistence.postgres.dto.PaymentSimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.entities.PaymentEntity;
import com.dad.sales_api.shared.persistence.postgres.entities.UserOrderEntity;
import com.dad.sales_api.shared.persistence.postgres.repositories.PaymentRepository;
import com.dad.sales_api.shared.persistence.postgres.repositories.UserOrderRepository;
import com.dad.sales_api.shared.persistence.postgres.specifications.PaymentSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
  private final PaymentRepository paymentRepository;
  private final UserOrderRepository userOrderRepository;
  private final MessageService messageService;

  public FindManyPaymentsOutputDTO findMany(FindManyPaymentsInputDTO input) {

    Specification<PaymentEntity> spec = Specification
        .where(PaymentSpecification.withUserId(input.userId()))
        .and(PaymentSpecification.withUserOrderId(input.userOrderId()))
        .and(PaymentSpecification.withPaymentStatus(input.paymentStatus()))
        .and(PaymentSpecification.withPaymentType(input.paymentType()));

    int count = (int) paymentRepository.count(spec);

    int totalPages = (int) Math.ceil((double) count / input.take());

    List<PaymentEntity> payments = paymentRepository.findAll(
        spec,
        PaginationHelper.calculatePage(
            input.page(),
            totalPages,
            input.take()
        )
    ).getContent();

    List<PaymentSimpleDTO> output = payments.stream()
        .map(PaymentMapper::convertEntityToSimpleDTO)
        .toList();

    return new FindManyPaymentsOutputDTO(
        output,
        totalPages,
        count
    );
  }

  @Transactional
  public PayOutputDTO pay(
      PayInputDTO input
  ){
    PaymentEntity payment = find(input.paymentId(), input.userId());

    if (payment.getPaymentStatus() != PaymentStatusEnum.PENDING) throw new ConflictException(
        messageService.getMessage("exception.payment-status.conflict")
    );

    if (LocalDate.now().isAfter(payment.getDueDate())) {
      payment.setPaymentStatus(PaymentStatusEnum.PAID_LATE);
    } else {
      payment.setPaymentStatus(PaymentStatusEnum.PAID);
    }

    payment.setPayDate(LocalDate.now());

    UserOrderEntity userOrder = payment.getUserOrder();

    if (userOrder.getInstallmentsCount() > 1){
      if (payment.getInstallmentNumber() == userOrder.getInstallmentsCount()) {
        userOrder.setStatus(OrderStatusEnum.PAID);
      } else {
        userOrder.setStatus(OrderStatusEnum.FINANCED);
      }
    } else {
      userOrder.setStatus(OrderStatusEnum.PAID);
    }

    userOrderRepository.save(userOrder);

    paymentRepository.save(payment);

    return new PayOutputDTO(
        payment.getId(),
        payment.getInstallmentNumber(),
        payment.getPayValue(),
        payment.getCreatedAt(),
        payment.getPayDate(),
        payment.getDueDate(),
        payment.getPaymentType(),
        payment.getPaymentStatus()
    );
  }

  private PaymentEntity find(Integer paymentId, Integer userId){
    return paymentRepository.findByIdAndUserOrder_User_Id(
        paymentId,
        userId
    ).orElseThrow(
        () -> new NotFoundException(
            messageService.getMessage("exception.payment.not-found")
        )
    );
  }
}
