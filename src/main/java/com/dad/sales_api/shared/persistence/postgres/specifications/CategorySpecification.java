package com.dad.sales_api.shared.persistence.postgres.specifications;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import com.dad.sales_api.shared.persistence.postgres.entities.CategoryEntity;

public class CategorySpecification {
  public static Specification<CategoryEntity> withName(String name) {
    return (root, query, cb) -> name == null ? null
      : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
  }

  public static Specification<CategoryEntity> withStatus(Boolean active) {
    return (root, query, cb) -> active == null ? null
      : cb.equal(root.get("active"), active);
  }

  public static Specification<CategoryEntity> withIds(List<Integer> ids){
    return (root, query, cb) -> root.get("id").in(ids);
  }
}
