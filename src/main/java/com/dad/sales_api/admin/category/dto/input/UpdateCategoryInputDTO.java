package com.dad.sales_api.admin.category.dto.input;

import com.dad.sales_api.admin.category.dto.request.UpdateCategoryRequestDTO;

public record UpdateCategoryInputDTO(
  Integer id,
  String name,
  String description,
  Boolean active
) {
  public UpdateCategoryInputDTO(Integer id, UpdateCategoryRequestDTO input){
    this(id, input.name(), input.description(), input.active());
  }
}
