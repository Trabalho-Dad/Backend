package com.dad.sales_api.admin.figure.dto.input;

import com.dad.sales_api.admin.figure.dto.request.CreateFigureRequestDTO;
import com.dad.sales_api.admin.figure.dto.request.UpdateFigureRequestDTO;
import com.dad.sales_api.admin.image.dto.input.CreateImageInputDTO;
import com.dad.sales_api.shared.helpers.NormalizeInput;

import java.math.BigDecimal;
import java.util.List;

public record UpdateFigureInputDTO(
    Integer id,
  String name,
  String description,
  BigDecimal price,
  Integer quantity,
  Boolean active,
  List<Integer> categoryIds
) {
  public UpdateFigureInputDTO(Integer id, UpdateFigureRequestDTO input){
    this(
      id,
      NormalizeInput.name(input.name()),
      NormalizeInput.description(input.description()),
      input.price(),
      input.quantity(),
      input.active(),
      input.categoryIds()
    );
  }
}
