package com.dad.sales_api.shared.persistence.postgres.dto;

import com.dad.sales_api.shared.persistence.postgres.entities.custom_id.UserOrderFigureId;

import java.math.BigDecimal;

public record UserOrderFigureSimpleDTO (
    UserOrderFigureId id,
    FigureSimpleDTO figure,
    Integer quantity,
    BigDecimal price
){
}
