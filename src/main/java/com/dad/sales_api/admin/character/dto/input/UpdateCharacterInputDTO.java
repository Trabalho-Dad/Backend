package com.dad.sales_api.admin.character.dto.input;

import com.dad.sales_api.admin.character.dto.request.UpdateCharacterRequestDTO;
import com.dad.sales_api.shared.helpers.NormalizeInput;

public record UpdateCharacterInputDTO(
  Integer id,
  String name,
  String description,
  Boolean active
) {
  public UpdateCharacterInputDTO(Integer id, UpdateCharacterRequestDTO input){
    this(
        id,
        NormalizeInput.name(input.name()),
        NormalizeInput.description(input.description()),
        input.active()
    );
  }
}
