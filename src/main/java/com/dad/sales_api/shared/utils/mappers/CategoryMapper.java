package com.dad.sales_api.shared.utils.mappers;

import com.dad.sales_api.shared.dto.CategorySimpleDTO;
import com.dad.sales_api.shared.entities.CategoryEntity;

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