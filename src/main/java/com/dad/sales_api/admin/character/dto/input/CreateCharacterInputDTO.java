package com.dad.sales_api.admin.character.dto.input;

import com.dad.sales_api.admin.character.dto.request.CreateCharacterRequestDTO;

public record CreateCharacterInputDTO(
  String name,
  String description,
  Boolean active
) {
  public CreateCharacterInputDTO(CreateCharacterRequestDTO input){
    this(input.name(), input.description(), input.active());
  }
}
