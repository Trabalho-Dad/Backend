package com.dad.sales_api.admin.coupon.dto.output;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FindCouponByIdOutputDTO(
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
