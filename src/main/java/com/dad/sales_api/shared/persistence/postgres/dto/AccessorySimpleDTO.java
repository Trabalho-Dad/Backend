package com.dad.sales_api.shared.persistence.postgres.dto;

import com.dad.sales_api.shared.helpers.NormalizeOutput;

public record AccessorySimpleDTO(
  Integer id,
  String name,
  String description,
  ImageSimpleDTO image
) {
  public AccessorySimpleDTO(Integer id, String name, String description, ImageSimpleDTO image) {
    this.id = id;
    this.name = NormalizeOutput.name(name);
    this.description = description;
    this.image = image;
  }
}
