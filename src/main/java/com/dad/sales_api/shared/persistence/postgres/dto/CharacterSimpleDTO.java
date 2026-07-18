package com.dad.sales_api.shared.persistence.postgres.dto;

import com.dad.sales_api.shared.helpers.NormalizeOutput;
import com.dad.sales_api.shared.persistence.postgres.entities.CharacterEntity;

public record CharacterSimpleDTO(
  Integer id,
  String name,
  String description,
  Boolean active
) {
  public CharacterSimpleDTO(CharacterEntity entity){
    this(
      entity.getId(),
      NormalizeOutput.name(entity.getName()),
      entity.getDescription(),
      entity.getActive()
    );
  }
}
