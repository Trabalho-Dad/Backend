package com.dad.sales_api.admin.coupon.dto.input;

public record FindManyCouponsInputDTO(
    String code,
    Boolean active,
    Integer page,
    Integer take
) {}