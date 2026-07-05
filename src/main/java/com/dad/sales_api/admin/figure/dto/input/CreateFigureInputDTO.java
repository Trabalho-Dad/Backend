package com.dad.sales_api.admin.figure.dto.input;

import java.math.BigDecimal;
import java.util.List;

import com.dad.sales_api.admin.figure.dto.request.CreateFigureRequestDTO;
import com.dad.sales_api.shared.helpers.NormalizeInput;

public record CreateFigureInputDTO(
  String name,
  String description,
  BigDecimal price,
  Integer quantity,
  Boolean active,
  Integer characterId,
  List<Integer> accessoryIds,
  List<Integer> categoryIds,
  List<Integer> imageIds
) {
  public CreateFigureInputDTO(CreateFigureRequestDTO input){
    this(
      NormalizeInput.name(input.name()),
      NormalizeInput.description(input.description()),
      input.price(),
      input.quantity(),
      input.active(),
      input.characterId(),
      input.accessoryIds(),
      input.categoryIds() ,
      input.imageIds()
    );
  }
}
