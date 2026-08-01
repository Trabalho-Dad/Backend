package com.dad.sales_api.payment.dto.output;

import com.dad.sales_api.shared.persistence.postgres.dto.PaymentSimpleDTO;
import java.util.List;

public record FindManyPaymentsOutputDTO(
    List<PaymentSimpleDTO> payments,
    Integer totalPages,
    Integer totalItems
) {
}