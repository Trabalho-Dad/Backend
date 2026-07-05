package com.dad.sales_api.shared.persistence.postgres.dto;

import com.dad.sales_api.shared.helpers.NormalizeOutput;

public record CategorySimpleDTO(
  Integer id,
  String name,
  String description,
  Boolean active
) {

  public CategorySimpleDTO(Integer id, String name, String description, Boolean active) {
    this.id = id;
    this.name = NormalizeOutput.name(name);
    this.description = description;
    this.active = active;
  }
}
