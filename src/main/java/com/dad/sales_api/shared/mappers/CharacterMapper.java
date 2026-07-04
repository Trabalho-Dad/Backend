package com.dad.sales_api.shared.mappers;

import com.dad.sales_api.shared.dto.CharacterSimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.entities.CharacterEntity;

public class CharacterMapper {
  public static CharacterSimpleDTO convertToSimpleDTO(CharacterEntity entity){
    return new CharacterSimpleDTO(
      entity.getId(),
      entity.getName(),
      entity.getDescription(),
      entity.getActive()
    );
  }
}
