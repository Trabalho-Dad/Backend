package com.dad.sales_api.shared.dto;

public record CharacterSimpleDTO(
  Integer id,
  String name,
  String description,
  Boolean active
) {
  
}
