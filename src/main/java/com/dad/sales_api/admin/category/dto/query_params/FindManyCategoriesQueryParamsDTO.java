package com.dad.sales_api.admin.category.dto.query_params;

import com.dad.sales_api.shared.helpers.NormalizeInput;
import com.dad.sales_api.shared.helpers.RegexPatterns;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

public record FindManyCategoriesQueryParamsDTO(
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
  public FindManyCategoriesQueryParamsDTO(String name, Boolean active, Integer page, Integer take) {
    this.name = NormalizeInput.name(name);
    this.active = active;
    this.page = page != null ? page : 1;
    this.take = take != null ? take : 4;
  }
}