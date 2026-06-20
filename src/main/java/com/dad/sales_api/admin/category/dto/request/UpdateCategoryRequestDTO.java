package com.dad.sales_api.admin.category.dto.request;

import com.dad.sales_api.shared.utils.SalesConstants;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

public record UpdateCategoryRequestDTO(
  @NotBlank
  @Size(min = SalesConstants.MIN_LENGTH_NAME, max = SalesConstants.MAX_LENGTH_NAME)
  String name,
  String description,
  Boolean active
) {
}