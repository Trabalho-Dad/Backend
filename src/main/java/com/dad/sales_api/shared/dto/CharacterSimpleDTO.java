package com.dad.sales_api.shared.dto;

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
      entity.getName(),
      entity.getDescription(),
      entity.getActive()
    );
  }
}
