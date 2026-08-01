package com.dad.sales_api.order.dto.request;

import com.dad.sales_api.shared.enums.PaymentTypeEnum;

import java.math.BigDecimal;

public record FinishOrderRequestDTO(
    Integer addressId,
    BigDecimal shippingCost,
    Integer estimatedDeliveryTime,
    Integer installmentsCount,
    PaymentTypeEnum paymentType
) {
}
