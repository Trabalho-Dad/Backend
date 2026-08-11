package com.dad.sales_api.admin.character.dto.input;

import java.util.List;

import com.dad.sales_api.admin.character.dto.request.CreateCharacterRequestDTO;
import com.dad.sales_api.shared.helpers.NormalizeInput;

public record CreateCharacterInputDTO(
    String name,
    String description,
    Boolean active
) {
  public CreateCharacterInputDTO(CreateCharacterRequestDTO input) {
    this(
        NormalizeInput.name(input.name()),
        NormalizeInput.description(input.description()),
        input.active()
    );
  }
}
