package com.dad.sales_api.order.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RemoveItemRequestDTO (
    @NotNull(message = "{validation.figure-id.required}")
    @Positive(message = "{validation.figure-id.min-value}")
    Integer figureId,

    @Positive(message = "{validation.quantity.min-value}")
    Integer quantity
){
}
