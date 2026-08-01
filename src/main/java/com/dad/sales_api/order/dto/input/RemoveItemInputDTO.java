package com.dad.sales_api.order.dto.input;

import com.dad.sales_api.order.dto.request.RemoveItemRequestDTO;

public record RemoveItemInputDTO(
    Integer userId,
    Integer figureId,
    Integer quantity
) {
  public RemoveItemInputDTO (RemoveItemRequestDTO input, Integer userId){
    this(
        userId,
        input.figureId(),
        input.quantity()
    );
  }
}
