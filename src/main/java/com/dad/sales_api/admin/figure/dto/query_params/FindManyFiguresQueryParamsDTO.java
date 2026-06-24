package com.dad.sales_api.admin.figure.dto.query_params;

import jakarta.validation.constraints.Min;

public record FindManyFiguresQueryParamsDTO(
  String name,
  Boolean active,
  @Min(1)
  Integer page,
  @Min(1)
  Integer take
) {
  public FindManyFiguresQueryParamsDTO {
    page = (page != null && page > 0) ? page : 1;
    take = (take != null && take > 0) ? take : 4;
  }
}