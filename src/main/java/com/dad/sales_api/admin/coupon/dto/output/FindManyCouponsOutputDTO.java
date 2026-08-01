package com.dad.sales_api.admin.coupon.dto.output;

import com.dad.sales_api.shared.persistence.postgres.dto.CharacterSimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.dto.CouponSimpleDTO;

import java.util.List;

public record FindManyCouponsOutputDTO(
  List<CouponSimpleDTO> coupons,
  Integer totalPages,
  Integer count
) {
  
}
