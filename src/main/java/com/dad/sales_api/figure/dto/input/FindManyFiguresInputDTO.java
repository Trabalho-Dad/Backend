package com.dad.sales_api.figure.dto.input;

import com.dad.sales_api.figure.dto.query_params.FindManyFiguresQueryParamsDTO;
import com.dad.sales_api.shared.helpers.NormalizeInput;

public record FindManyFiguresInputDTO(
  String name,
  Boolean active,
  Integer page,
  Integer take
) {
  public FindManyFiguresInputDTO(FindManyFiguresQueryParamsDTO query){
    this(
      NormalizeInput.name(query.name()),
      query.active(),
      query.page(),
      query.take()
    );
  }
}
