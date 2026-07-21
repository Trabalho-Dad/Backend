package com.dad.sales_api.shared.persistence.postgres.dto;

import com.dad.sales_api.shared.enums.OrderStatusEnum;
import com.dad.sales_api.shared.persistence.postgres.entities.custom_id.UserOrderFigureId;

import java.math.BigDecimal;

public record UserOrderSimpleDTO(
    Integer id,
    BigDecimal price,
    BigDecimal finalPrice,
    BigDecimal discount,
    OrderStatusEnum status
) {
}
