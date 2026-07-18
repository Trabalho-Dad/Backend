package com.dad.sales_api.order.dto.query_params;

import com.dad.sales_api.shared.enums.OrderStatusEnum;
import jakarta.validation.constraints.Min;

public record FindManyOrdersQueryParamsDTO(
    OrderStatusEnum status,
    @Min(1)
    Integer page,
    @Min(1)
    Integer take
) {
  public FindManyOrdersQueryParamsDTO {
    page = (page != null && page > 0) ? page : 1;
    take = (take != null && take > 0) ? take : 4;
  }
}
