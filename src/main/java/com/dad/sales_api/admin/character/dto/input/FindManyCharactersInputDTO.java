package com.dad.sales_api.admin.character.dto.input;

import com.dad.sales_api.admin.character.dto.query_params.FindManyCharactersQueryParamsDTO;

public record FindManyCharactersInputDTO(
  String name,
  Boolean active,
  Integer page,
  Integer take
) {
  public FindManyCharactersInputDTO(FindManyCharactersQueryParamsDTO query){
    this(query.name(), query.active(), query.page(), query.take());
  } 
}