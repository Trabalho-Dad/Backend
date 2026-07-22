package com.dad.sales_api.shared.persistence.postgres.dto;

import com.dad.sales_api.shared.enums.PaymentTypeEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PaymentSimpleDTO (
    Integer id,
    BigDecimal payValue,
    LocalDate payDate,
    LocalDate dueDate,
    PaymentTypeEnum paymentType
){
}
