package com.dad.sales_api.shared.persistence.postgres.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CouponSimpleDTO(
    Integer id,
    String code,
    BigDecimal discountPct,
    Integer usageLimit,
    Integer usageCount,
    Boolean active,
    LocalDate startDate,
    LocalDate endDate
) {
}
