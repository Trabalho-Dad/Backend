package com.dad.sales_api.shared.mappers;

import com.dad.sales_api.shared.dto.CategorySimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.entities.CategoryEntity;

public class CategoryMapper {
  public static CategorySimpleDTO convertEntityToSimpleDTO(CategoryEntity entity){
    return new CategorySimpleDTO(
      entity.getId(),
      entity.getName(),
      entity.getDescription(),
      entity.getActive()
    );
  }
}