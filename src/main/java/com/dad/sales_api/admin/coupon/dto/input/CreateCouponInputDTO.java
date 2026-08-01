package com.dad.sales_api.admin.coupon.dto.input;

import com.dad.sales_api.admin.coupon.dto.request.CreateCouponRequestDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateCouponInputDTO(
    String code,
    BigDecimal discountPct,
    Integer usageLimit,
    LocalDate startDate,
    LocalDate endDate
) {
  public CreateCouponInputDTO (CreateCouponRequestDTO input){
    this(
        input.code(),
        input.discountPct(),
        input.usageLimit(),
        input.startDate() != null ? input.startDate() : LocalDate.now(),
        input.endDate()
    );
  }
}
