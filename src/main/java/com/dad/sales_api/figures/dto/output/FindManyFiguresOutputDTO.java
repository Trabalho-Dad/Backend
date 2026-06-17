package com.dad.sales_api.figures.dto.output;

import java.util.List;

import com.dad.sales_api.shared.dto.FigureSimpleDTO;

public record FindManyFiguresOutputDTO(
  List<FigureSimpleDTO> figures,
  Integer totalPages,
  Integer count
) {
}