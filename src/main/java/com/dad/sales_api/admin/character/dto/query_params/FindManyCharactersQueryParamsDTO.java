package com.dad.sales_api.admin.character.dto.query_params;

import com.dad.sales_api.shared.helpers.RegexPatterns;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

public record FindManyCharactersQueryParamsDTO(
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
  public FindManyCharactersQueryParamsDTO(String name, Boolean active, Integer page, Integer take) {
    this.name = name;
    this.active = active;
    this.page = page != null ? page : 1;
    this.take = take != null ? take : 4;
  }
}
