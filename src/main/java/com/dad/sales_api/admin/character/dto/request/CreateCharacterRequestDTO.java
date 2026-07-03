package com.dad.sales_api.admin.character.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

import com.dad.sales_api.shared.SalesConstants;

public record CreateCharacterRequestDTO(
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
  Boolean active,
  List<Integer> imageIds
) {
  public CreateCharacterRequestDTO(String name, String description, Boolean active, List<Integer> imageIds){
    this.name = name != null ? name : "";
    this.description = description != null ? description : "";
    this.active = active != null ? active : true;
    this.imageIds = imageIds;
  }
}
