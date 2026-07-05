package com.dad.sales_api.admin.category.dto.input;

import com.dad.sales_api.admin.category.dto.request.UpdateCategoryRequestDTO;
import com.dad.sales_api.shared.helpers.NormalizeInput;

public record UpdateCategoryInputDTO(
  Integer id,
  String name,
  String description,
  Boolean active
) {
  public UpdateCategoryInputDTO(Integer id, UpdateCategoryRequestDTO input){
    this(
        id,
        NormalizeInput.name(input.name()),
        NormalizeInput.description(input.description()),
        input.active()
    );
  }
}
