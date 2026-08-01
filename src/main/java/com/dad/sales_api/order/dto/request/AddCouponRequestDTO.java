package com.dad.sales_api.order.dto.request;

public record AddCouponRequestDTO(
    Integer orderId,
    String code
) {
}
