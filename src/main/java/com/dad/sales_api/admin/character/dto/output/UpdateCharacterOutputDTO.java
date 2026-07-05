package com.dad.sales_api.admin.character.dto.output;

import com.dad.sales_api.shared.helpers.NormalizeOutput;

public record UpdateCharacterOutputDTO(
  Integer id,
  String name,
  String description,
  Boolean active
) {
  public UpdateCharacterOutputDTO(Integer id, String name, String description, Boolean active) {
    this.id = id;
    this.name = NormalizeOutput.name(name);
    this.description = description;
    this.active = active;
  }
}
