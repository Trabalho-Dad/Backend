package com.dad.sales_api.shared.dto;

public record AccessorySimpleDTO(
  Integer id,
  String name,
  String description,
  ImageSimpleDTO image
) {
  
}
