package com.dad.sales_api.shared.persistence.postgres.specifications;

import com.dad.sales_api.shared.enums.PaymentStatusEnum;
import com.dad.sales_api.shared.enums.PaymentTypeEnum;
import com.dad.sales_api.shared.persistence.postgres.entities.PaymentEntity;
import org.springframework.data.jpa.domain.Specification;

public class PaymentSpecification {

  public static Specification<PaymentEntity> withUserId(Integer userId) {
    return (root, query, cb) ->
        userId == null
            ? cb.conjunction()
            : cb.equal(root.get("userOrder").get("user").get("id"), userId);
  }

  public static Specification<PaymentEntity> withUserOrderId(Integer userOrderId) {
    return (root, query, cb) ->
        userOrderId == null
            ? cb.conjunction()
            : cb.equal(root.get("userOrder").get("id"), userOrderId);
  }

  public static Specification<PaymentEntity> withPaymentStatus(PaymentStatusEnum status) {
    return (root, query, cb) ->
        status == null
            ? cb.conjunction()
            : cb.equal(root.get("paymentStatus"), status);
  }

  public static Specification<PaymentEntity> withPaymentType(PaymentTypeEnum type) {
    return (root, query, cb) ->
        type == null
            ? cb.conjunction()
            : cb.equal(root.get("paymentType"), type);
  }
}