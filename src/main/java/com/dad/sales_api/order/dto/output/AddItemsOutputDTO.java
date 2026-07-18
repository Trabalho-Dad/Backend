package com.dad.sales_api.order.dto.output;

import java.math.BigDecimal;

public record AddItemsOutputDTO(
    Integer orderId,
    BigDecimal totalPrice,
    Integer figureId,
    String figureName,
    BigDecimal unitaryPrice,
    Integer quantity,
    BigDecimal figuresPrice
) {
}
