package com.dad.sales_api.admin.category.dto.output;

import com.dad.sales_api.shared.helpers.NormalizeOutput;
import com.dad.sales_api.shared.persistence.postgres.entities.CategoryEntity;

public record UpdateCategoryOutputDTO (
  Integer id,
  String name,
  String description,
  Boolean active
) {
  public UpdateCategoryOutputDTO(CategoryEntity entity){
    this(
        entity.getId(),
        NormalizeOutput.name(entity.getName()),
        entity.getDescription(),
        entity.getActive()
    );
  }
}
