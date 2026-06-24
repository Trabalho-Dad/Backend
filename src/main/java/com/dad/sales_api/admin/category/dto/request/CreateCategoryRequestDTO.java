package com.dad.sales_api.admin.category.dto.request;

import com.dad.sales_api.shared.utils.SalesConstants;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

public record CreateCategoryRequestDTO(
  @NotBlank
  @Size(min = SalesConstants.MIN_LENGTH_NAME, max = SalesConstants.MAX_LENGTH_NAME)
  String name,
  String description,
  Boolean active
) {
  public CreateCategoryRequestDTO(String name, String description, Boolean active){
    this.name = name != null ? name : "";
    this.description = description != null ? description : "";
    this.active = active != null ? active : true;
  }
}