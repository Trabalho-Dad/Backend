package com.dad.sales_api.admin.character.dto.input;

import com.dad.sales_api.admin.character.dto.query_params.FindManyCharactersQueryParamsDTO;
import com.dad.sales_api.shared.helpers.NormalizeInput;

public record FindManyCharactersInputDTO(
    String name,
    Boolean active,
    Integer page,
    Integer take
) {
  public FindManyCharactersInputDTO(FindManyCharactersQueryParamsDTO query) {
    this(
        NormalizeInput.name(query.name()),
        query.active(),
        query.page(),
        query.take())
    ;
  }
}