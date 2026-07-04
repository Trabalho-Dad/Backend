package com.dad.sales_api.admin.category.dto.input;

import com.dad.sales_api.admin.category.dto.request.CreateCategoryRequestDTO;

public record CreateCategoryInputDTO(
  String name,
  String description,
  Boolean active
) {
  public CreateCategoryInputDTO(CreateCategoryRequestDTO request){
    this(request.name(), request.description(), request.active());
  }
}
