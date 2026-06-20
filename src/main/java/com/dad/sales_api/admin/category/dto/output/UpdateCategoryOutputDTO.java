package com.dad.sales_api.admin.category.dto.output;

import com.dad.sales_api.shared.entities.CategoryEntity;

public record UpdateCategoryOutputDTO (
  Integer id,
  String name,
  String description,
  Boolean active
) {
  public UpdateCategoryOutputDTO(CategoryEntity entity){
    this(entity.getId(), entity.getName(), entity.getDescription(), entity.getActive());
  }
}
