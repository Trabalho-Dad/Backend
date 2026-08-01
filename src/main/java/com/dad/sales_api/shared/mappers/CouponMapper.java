package com.dad.sales_api.shared.mappers;

import com.dad.sales_api.shared.persistence.postgres.dto.CouponSimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.entities.CouponEntity;

import java.time.LocalDate;

public class CouponMapper {
  public static CouponSimpleDTO convertEntityToSimpleDTO(CouponEntity entity){
    return new CouponSimpleDTO(
        entity.getId(),
        entity.getCode(),
        entity.getDiscountPct(),
        entity.getUsageLimit(),
        entity.getUsageCount(),
        entity.getActive(),
        entity.getStartDate(),
        entity.getEndDate()
    );
  }
}
