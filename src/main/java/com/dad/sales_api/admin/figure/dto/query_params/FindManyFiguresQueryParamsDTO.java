package com.dad.sales_api.admin.figure.dto.query_params;

import com.dad.sales_api.shared.helpers.RegexPatterns;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

public record FindManyFiguresQueryParamsDTO(
  @Pattern(
      regexp = RegexPatterns.NAME,
      message = "{validation.name.regex}"
  )
  String name,
  Boolean active,
  @Min(value = 1, message = "{validation.page.min-value}")
  Integer page,

  @Min(value = 1, message = "{validation.take.min-value}")
  Integer take
) {
  public FindManyFiguresQueryParamsDTO {
    page = (page != null && page > 0) ? page : 1;
    take = (take != null && take > 0) ? take : 4;
  }
}