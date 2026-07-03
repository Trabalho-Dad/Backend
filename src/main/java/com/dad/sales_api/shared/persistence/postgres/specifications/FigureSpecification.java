package com.dad.sales_api.shared.persistence.postgres.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.dad.sales_api.shared.persistence.postgres.entities.FigureEntity;

public class FigureSpecification {
  public static Specification<FigureEntity> withName(String name) {
    return (root, query, cb) -> name == null ? null
      : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
  }

  public static Specification<FigureEntity> withStatus(Boolean active) {
    return (root, query, cb) -> active == null ? null
      : cb.equal(root.get("active"), active);
  }
}
