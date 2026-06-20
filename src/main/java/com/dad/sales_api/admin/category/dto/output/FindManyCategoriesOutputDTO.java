package com.dad.sales_api.admin.category.dto.output;

import java.util.List;

import com.dad.sales_api.shared.dto.CategorySimpleDTO;

public record FindManyCategoriesOutputDTO(
  List<CategorySimpleDTO> categories,
  Integer totalPages,
  Integer count
) {
  
}
