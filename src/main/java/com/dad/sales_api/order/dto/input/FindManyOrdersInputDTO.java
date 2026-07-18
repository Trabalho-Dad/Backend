package com.dad.sales_api.order.dto.input;

import com.dad.sales_api.order.dto.query_params.FindManyOrdersQueryParamsDTO;
import com.dad.sales_api.shared.enums.OrderStatusEnum;

public record FindManyOrdersInputDTO(
    Integer userId,
    OrderStatusEnum status,
    Integer page,
    Integer take
) {
  public FindManyOrdersInputDTO (FindManyOrdersQueryParamsDTO query, Integer userId){
    this(
        userId,
        query.status(),
        query.page(),
        query.take()
    );
  }
}
