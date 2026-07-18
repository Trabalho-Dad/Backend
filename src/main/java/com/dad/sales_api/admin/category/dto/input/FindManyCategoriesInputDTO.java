package com.dad.sales_api.admin.category.dto.input;

import com.dad.sales_api.admin.category.dto.query_params.FindManyCategoriesQueryParamsDTO;
import com.dad.sales_api.shared.helpers.NormalizeInput;

public record FindManyCategoriesInputDTO(
    String name,
    Boolean active,
    Integer page,
    Integer take
) {
  public FindManyCategoriesInputDTO(FindManyCategoriesQueryParamsDTO query) {
    this(
        NormalizeInput.name(query.name()),
        query.active(),
        query.page(),
        query.take()
    );
  }
}