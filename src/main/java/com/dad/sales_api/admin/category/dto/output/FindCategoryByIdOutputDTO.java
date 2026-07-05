package com.dad.sales_api.admin.category.dto.output;

import java.util.List;

import com.dad.sales_api.shared.persistence.postgres.dto.FigureSimpleDTO;
import com.dad.sales_api.shared.helpers.NormalizeOutput;

public record FindCategoryByIdOutputDTO(
  Integer id,
  String name,
  String description,
  Boolean active,
  List<FigureSimpleDTO> figures
) {
  public FindCategoryByIdOutputDTO(Integer id, String name, String description, Boolean active, List<FigureSimpleDTO> figures) {
    this.id = id;
    this.name = NormalizeOutput.name(name);
    this.description = description;
    this.active = active;
    this.figures = figures;
  }
}
