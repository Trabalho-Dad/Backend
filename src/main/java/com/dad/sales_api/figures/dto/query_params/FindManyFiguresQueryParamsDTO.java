package com.dad.sales_api.figures.dto.query_params;

import jakarta.validation.constraints.Min;

public record FindManyFiguresQueryParamsDTO(
  String name,
  Boolean active,
  @Min(1)
  Integer page,
  @Min(1)
  Integer take
) {
}