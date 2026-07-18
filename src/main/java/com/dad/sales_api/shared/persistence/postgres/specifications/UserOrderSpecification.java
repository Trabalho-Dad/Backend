package com.dad.sales_api.shared.persistence.postgres.specifications;

import com.dad.sales_api.shared.enums.OrderStatusEnum;
import com.dad.sales_api.shared.persistence.postgres.entities.UserOrderEntity;
import org.springframework.data.jpa.domain.Specification;

public class UserOrderSpecification {
  public static Specification<UserOrderEntity> withUserId(Integer userId){
    return (root, query, cb) -> userId == null ? null
        : cb.equal(root.get("user").get("id"), userId);
  }

  public static Specification<UserOrderEntity> withStatus(OrderStatusEnum status){
    return (root, query, cb) -> status == null ? null
        : cb.equal(root.get("status"), status);
  }

  public static Specification<UserOrderEntity> withId(Integer id){
    return (root, query, cb) -> id == null ? null
        : cb.equal(root.get("id"), id);
  }
}
