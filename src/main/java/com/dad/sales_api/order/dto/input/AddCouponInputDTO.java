package com.dad.sales_api.order.dto.input;

public record AddCouponInputDTO(
    Integer userId,
    Integer orderId,
    String code
) {
}
