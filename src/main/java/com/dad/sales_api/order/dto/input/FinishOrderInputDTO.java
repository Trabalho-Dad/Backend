package com.dad.sales_api.order.dto.input;

import com.dad.sales_api.order.dto.request.FinishOrderRequestDTO;
import com.dad.sales_api.shared.enums.PaymentTypeEnum;

import java.math.BigDecimal;

public record FinishOrderInputDTO(
    Integer userId,
    Integer addressId,
    BigDecimal shippingCost,
    Integer estimatedDeliveryTime,
    Integer installmentsCount,
    PaymentTypeEnum paymentType
) {
  public FinishOrderInputDTO(Integer userId, FinishOrderRequestDTO request) {
    this(
        userId,
        request.addressId(),
        request.shippingCost(),
        request.estimatedDeliveryTime(),
        request.installmentsCount(),
        request.paymentType()
    );
  }
}
