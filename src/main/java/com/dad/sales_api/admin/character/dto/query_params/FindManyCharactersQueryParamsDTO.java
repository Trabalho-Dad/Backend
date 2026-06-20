package com.dad.sales_api.admin.character.dto.query_params;

import jakarta.validation.constraints.Min;

public record FindManyCharactersQueryParamsDTO(
  String name,
  Boolean active,
  @Min(1)
  Integer page,

  @Min(1)
  Integer take
) {
  public FindManyCharactersQueryParamsDTO(String name, Boolean active, Integer page, Integer take) {
    this.name = name;
        this.active = active;
        this.page = page != null ? page : 1;
        this.take = take != null ? take : 4;
  }
}
