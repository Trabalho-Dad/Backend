package com.dad.sales_api.admin.category.dto.input;

import com.dad.sales_api.admin.category.dto.request.CreateCategoryRequestDTO;
import com.dad.sales_api.shared.helpers.NormalizeInput;

public record CreateCategoryInputDTO(
  String name,
  String description,
  Boolean active
) {
  public CreateCategoryInputDTO(CreateCategoryRequestDTO input){
    this(
        NormalizeInput.name(input.name()),
        NormalizeInput.description(input.description()),
        input.active()
    );
  }
}
