package com.dad.sales_api.order.dto.output;

import com.dad.sales_api.shared.persistence.postgres.dto.UserOrderSimpleDTO;

import java.util.List;

public record FindManyOrdersOutputDTO(
    List<UserOrderSimpleDTO> orders,
    Integer totalPages,
    Integer count
) {
}
