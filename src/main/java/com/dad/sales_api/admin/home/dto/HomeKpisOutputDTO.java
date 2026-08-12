package com.dad.sales_api.admin.home.dto;

import java.math.BigDecimal;

public record HomeKpisOutputDTO(
    long ordersLast24h,
    BigDecimal totalReceivedLast24h,
    long totalActiveFigures
) {}