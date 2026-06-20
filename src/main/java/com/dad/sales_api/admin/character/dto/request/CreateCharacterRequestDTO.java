package com.dad.sales_api.admin.character.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.dad.sales_api.shared.utils.SalesConstants;

public record CreateCharacterRequestDTO(
  @NotBlank
  @Size(min = SalesConstants.MIN_LENGTH_NAME, max = SalesConstants.MAX_LENGTH_NAME)
  String name,
  String description,
  Boolean active
) {
  public CreateCharacterRequestDTO(String name, String description, Boolean active){
    this.name = name != null ? name : "";
    this.description = description != null ? description : "";
    this.active = active != null ? active : true;
  }
}
