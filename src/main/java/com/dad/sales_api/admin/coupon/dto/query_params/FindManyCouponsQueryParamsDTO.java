package com.dad.sales_api.admin.coupon.dto.query_params;

import jakarta.validation.constraints.Min;

public record FindManyCouponsQueryParamsDTO(
    String code,
    Boolean active,
    @Min(value = 1, message = "{validation.page.min-value}")
    Integer page,

    @Min(value = 1, message = "{validation.take.min-value}")
    Integer take
) {
  public FindManyCouponsQueryParamsDTO{
    page = (page != null && page > 0) ? page : 1;
    take = (take != null && take > 0) ? take : 4;
  }
}
