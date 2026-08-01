package com.dad.sales_api.payment.dto.query_params;

import com.dad.sales_api.shared.enums.PaymentStatusEnum;
import com.dad.sales_api.shared.enums.PaymentTypeEnum;
import jakarta.validation.constraints.Min;

public record FindManyPaymentsQueryParamsDTO(
    Integer userOrderId,
    PaymentStatusEnum paymentStatus,
    PaymentTypeEnum paymentType,

    @Min(value = 1, message = "{validation.page.min-value}")
    Integer page,

    @Min(value = 1, message = "{validation.take.min-value}")
    Integer take
) {

  public FindManyPaymentsQueryParamsDTO {
    page = (page != null && page > 0) ? page : 1;
    take = (take != null && take > 0) ? take : 4;
  }

}