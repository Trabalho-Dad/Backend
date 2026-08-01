package com.dad.sales_api.order.dto.output;

import java.math.BigDecimal;

public record AddCouponOutputDTO (
    Integer couponId,
    Integer orderId,
    BigDecimal couponDiscountPct,
    BigDecimal totalDiscountValue,
    BigDecimal price,
    BigDecimal totalPrice

){

}
