package com.dad.sales_api.shared.mappers;

import com.dad.sales_api.shared.helpers.NormalizeOutput;
import com.dad.sales_api.shared.persistence.postgres.dto.CharacterSimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.entities.CharacterEntity;

public class CharacterMapper {
  public static CharacterSimpleDTO convertToSimpleDTO(CharacterEntity entity){
    return new CharacterSimpleDTO(
      entity.getId(),
      NormalizeOutput.name(entity.getName()),
      entity.getDescription(),
      entity.getActive()
    );
  }
}
