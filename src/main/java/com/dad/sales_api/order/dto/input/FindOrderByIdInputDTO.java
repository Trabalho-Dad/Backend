package com.dad.sales_api.order.dto.input;

public record FindOrderByIdInputDTO (
    Integer id,
    Integer userId
){
}
