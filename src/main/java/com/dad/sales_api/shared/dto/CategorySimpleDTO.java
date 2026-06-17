package com.dad.sales_api.shared.dto;

public record CategorySimpleDTO(
  Integer id,
  String name,
  String description,
  Boolean active
) {
  
}
