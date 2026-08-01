package com.dad.sales_api.admin.coupon.dto.request;

import com.dad.sales_api.shared.SalesConstants;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateCouponRequestDTO (
    @NotEmpty(message = "{validation.code.required}")
    @Size(
        min = SalesConstants.MIN_CODE_LENGTH,
        max = SalesConstants.MAX_CODE_LENGTH,
        message = "{validation.code.size}"
    )
    String code,
    @NotNull(message = "{validation.discount-pct.required}")
    @DecimalMin(value = "0.0", message = "{validation.discount-pct.min-value}")
    @DecimalMax(value = "1.0", inclusive = true, message = "{validation.discount-pct.max-value}")
    BigDecimal discountPct,
    @NotNull(message = "{validation.usage-limit.required}")
    @Positive(message = "{validation.usage-limit.min-value}")
    Integer usageLimit,

    LocalDate startDate,

    @NotNull(message = "{validation.end-date.required}")
    LocalDate endDate
){
}
