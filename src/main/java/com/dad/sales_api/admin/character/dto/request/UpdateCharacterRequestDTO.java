package com.dad.sales_api.admin.character.dto.request;

import com.dad.sales_api.shared.utils.SalesConstants;

import jakarta.validation.constraints.Size;

public record UpdateCharacterRequestDTO(
  @Size(min = SalesConstants.MIN_LENGTH_NAME, max = SalesConstants.MIN_LENGTH_NAME)
  String name,
  String description,
  Boolean active
) {
  
}
