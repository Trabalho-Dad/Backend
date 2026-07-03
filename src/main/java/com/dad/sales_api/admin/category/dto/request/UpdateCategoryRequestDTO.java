package com.dad.sales_api.admin.category.dto.request;

import com.dad.sales_api.shared.SalesConstants;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

public record UpdateCategoryRequestDTO(
  @NotBlank
  @Size(min = SalesConstants.MIN_NAME_LENGTH, max = SalesConstants.MAX_NAME_LENGTH)
  String name,
  String description,
  Boolean active
) {
}