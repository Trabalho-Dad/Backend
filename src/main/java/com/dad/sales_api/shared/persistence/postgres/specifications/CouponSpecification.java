package com.dad.sales_api.shared.persistence.postgres.specifications;

import com.dad.sales_api.shared.persistence.postgres.entities.CouponEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class CouponSpecification {
  public static Specification<CouponEntity> withCode(String code) {
    return (root, query, cb) -> code == null ? cb.conjunction()
        : cb.equal(cb.lower(root.get("code")), code.toLowerCase());
  }

  public static Specification<CouponEntity> available(Boolean active) {
    if (active == null) {
      return (root, query, cb) -> cb.conjunction();
    }

    if (active) {
      return (root, query, cb) -> cb.greaterThan(root.get("usageLimit"), root.get("usageCount"));
    } else {
      return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("usageLimit"), root.get("usageCount"));
    }
  }

  public static Specification<CouponEntity> validDate(LocalDate date) {
    return (root, query, cb) -> cb.and(
        cb.lessThanOrEqualTo(root.get("startDate"), date),
        cb.greaterThanOrEqualTo(root.get("endDate"), date)
    );
  }

  public static Specification<CouponEntity> invalidDate(LocalDate date) {
    return (root, query, cb) -> cb.or(
        cb.greaterThan(root.get("startDate"), date),
        cb.lessThan(root.get("endDate"), date)
    );
  }
}
