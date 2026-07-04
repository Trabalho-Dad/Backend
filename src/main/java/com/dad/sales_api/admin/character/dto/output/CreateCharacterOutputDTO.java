package com.dad.sales_api.admin.character.dto.output;

public record CreateCharacterOutputDTO(
  Integer id,
  String name,
  String description,
  Boolean active
) {
  
}
