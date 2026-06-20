package com.dad.sales_api.admin.figure.dto.input;

import java.math.BigDecimal;
import java.util.List;

import com.dad.sales_api.admin.figure.dto.request.CreateFigureRequestDTO;

public record CreateFigureInputDTO(
  String name,
  String description,
  BigDecimal price,
  String imgUrl,
  Integer quantity,
  Boolean active,
  Integer characterId,
  List<Integer> accessoryIds,
  List<Integer> categoryIds
) {
  public CreateFigureInputDTO(CreateFigureRequestDTO input){
    this(
      input.name(),
      input.description(),
      input.price(),
      input.imgUrl(),
      input.quantity(),
      input.active(),
      input.characterId(),
      input.accessoryIds(),
      input.categoryIds() 
    );
  }
}
