package com.dad.sales_api.shared.mappers;

import com.dad.sales_api.shared.persistence.postgres.dto.AccessorySimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.entities.AccessoryEntity;

public class AccessoryMapper {
  public static AccessorySimpleDTO convertToSimpleDTO(AccessoryEntity entity){
    return new AccessorySimpleDTO(
      entity.getId(),
      entity.getName(),
      entity.getDescription(),
      ImageMapper.convertEntityToSimpleDTO(entity.getImage())
    );
  } 
}
