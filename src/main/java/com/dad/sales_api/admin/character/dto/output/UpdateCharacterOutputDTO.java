package com.dad.sales_api.admin.character.dto.output;

public record UpdateCharacterOutputDTO(
  Integer id,
  String name,
  String description,
  Boolean active
) {
  
}
