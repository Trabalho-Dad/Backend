package com.dad.sales_api.admin.character.dto.input;

import com.dad.sales_api.admin.character.dto.request.UpdateCharacterRequestDTO;

public record UpdateCharacterInputDTO(
  Integer id,
  String name,
  String description,
  Boolean active
) {
  public UpdateCharacterInputDTO(Integer id, UpdateCharacterRequestDTO input){
    this(id, input.name(), input.description(), input.active());
  }
}
