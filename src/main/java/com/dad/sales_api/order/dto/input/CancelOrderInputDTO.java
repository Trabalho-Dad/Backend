package com.dad.sales_api.order.dto.input;

public record CancelOrderInputDTO(
    Integer orderId,
    Integer userId
) {
}
