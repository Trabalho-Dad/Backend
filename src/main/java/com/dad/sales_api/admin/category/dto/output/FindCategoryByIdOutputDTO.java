package com.dad.sales_api.admin.category.dto.output;

import java.util.List;

import com.dad.sales_api.shared.dto.FigureSimpleDTO;

public record FindCategoryByIdOutputDTO(
  Integer id,
  String name,
  String description,
  Boolean active,
  List<FigureSimpleDTO> figures
) {
  
}
