package com.dad.sales_api.admin.character.dto.output;

import java.util.List;

import com.dad.sales_api.shared.dto.CharacterSimpleDTO;

public record FindManyCharacterOutputDTO(
  List<CharacterSimpleDTO> characters,
  Integer totalPages,
  Integer count
) {
  
}
