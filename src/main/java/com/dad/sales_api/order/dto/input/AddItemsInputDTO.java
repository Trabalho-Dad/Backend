package com.dad.sales_api.order.dto.input;

import com.dad.sales_api.order.dto.request.AddItemsRequestDTO;

public record AddItemsInputDTO(
    Integer userId,
    Integer figureId,
    Integer quantity
) {
  public AddItemsInputDTO (AddItemsRequestDTO input, Integer userId){
    this(
        userId,
        input.figureId(),
        input.quantity()
    );
  }
}
