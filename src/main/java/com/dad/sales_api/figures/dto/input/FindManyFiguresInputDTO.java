package com.dad.sales_api.figures.dto.input;

import com.dad.sales_api.figures.dto.query_params.FindManyFiguresQueryParamsDTO;

public record FindManyFiguresInputDTO(
  String name,
  Boolean active,
  Integer page,
  Integer take
) {
  public FindManyFiguresInputDTO(FindManyFiguresQueryParamsDTO query){
    this(
      query.name(), 
      query.active(),
      query.page() != null && query.page() > 0 ? query.page() : 1,
      query.take() != null && query.take() > 0 ? query.take() : 4
    );
  }
}
