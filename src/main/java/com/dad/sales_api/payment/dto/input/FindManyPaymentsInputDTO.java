package com.dad.sales_api.payment.dto.input;

import com.dad.sales_api.payment.dto.query_params.FindManyPaymentsQueryParamsDTO;
import com.dad.sales_api.shared.enums.PaymentStatusEnum;
import com.dad.sales_api.shared.enums.PaymentTypeEnum;

public record FindManyPaymentsInputDTO(
    Integer userId,
    Integer userOrderId,
    PaymentStatusEnum paymentStatus,
    PaymentTypeEnum paymentType,
    Integer page,
    Integer take
) {
  public FindManyPaymentsInputDTO(FindManyPaymentsQueryParamsDTO query, Integer userId) {
    this(
        userId,
        query.userOrderId(),
        query.paymentStatus(),
        query.paymentType(),
        query.page(),
        query.take()
    );
  }
}