package com.dad.sales_api.shared.utils.mappers;

import com.dad.sales_api.shared.dto.AccessorySimpleDTO;
import com.dad.sales_api.shared.entities.AccessoryEntity;

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
