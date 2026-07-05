package com.dad.sales_api.admin.figure.dto.output;

import java.util.List;

import com.dad.sales_api.shared.persistence.postgres.dto.FigureSimpleDTO;

public record FindManyFiguresOutputDTO(
  List<FigureSimpleDTO> figures,
  Integer totalPages,
  Integer count
) {
  
}
