package com.dad.sales_api.admin.character.dto.request;

import com.dad.sales_api.shared.SalesConstants;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateCharacterRequestDTO(
  @NotBlank
  @Size(
      min = SalesConstants.MIN_NAME_LENGTH,
      max = SalesConstants.MAX_NAME_LENGTH,
      message = "O nome do personagem deve ter entre " +
          SalesConstants.MIN_NAME_LENGTH + " e " +
          SalesConstants.MAX_NAME_LENGTH + " caracteres"
  )
  String name,
  String description,
  Boolean active
) {
  
}
