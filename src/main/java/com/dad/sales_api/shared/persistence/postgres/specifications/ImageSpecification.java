package com.dad.sales_api.shared.persistence.postgres.specifications;

import com.dad.sales_api.shared.persistence.postgres.entities.ImageEntity;
import com.dad.sales_api.shared.enums.ImageTypeEnum;
import org.springframework.data.jpa.domain.Specification;

public class ImageSpecification {
  public static Specification<ImageEntity> containingDescription(String description) {
    return (root, query, cb) -> description == null ? null
        : cb.like(cb.lower(root.get("decription")), "%" + description.toLowerCase() + "%");
  }

  public static Specification<ImageEntity> withType(ImageTypeEnum type) {
    return (root, query, cb) -> type == null ? null
      : cb.equal(root.get("imageType"), type);
  }
}
