package com.dad.sales_api.admin.figure.dto.input;

import com.dad.sales_api.admin.figure.dto.query_params.FindManyFiguresQueryParamsDTO;

public record FindManyFiguresInputDTO(
  String name,
  Boolean active,
  Integer page,
  Integer take
) {
  public FindManyFiguresInputDTO(FindManyFiguresQueryParamsDTO query){
    this(query.name(), query.active(), query.page(), query.take());
  }
}
