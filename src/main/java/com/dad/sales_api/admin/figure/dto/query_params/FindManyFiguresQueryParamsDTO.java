package com.dad.sales_api.admin.figure.dto.query_params;

import com.dad.sales_api.shared.helpers.RegexPatterns;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

public record FindManyFiguresQueryParamsDTO(
  @Pattern(
      regexp = RegexPatterns.NAME,
      message = "O nome deve conter apenas letras acentuadas ou não."
  )
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