package com.dad.sales_api.shared.persistence.postgres.dto;

import com.dad.sales_api.shared.enums.PaymentStatusEnum;
import com.dad.sales_api.shared.enums.PaymentTypeEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record PaymentSimpleDTO (
    Integer id,
    Integer installmentNumber,
    BigDecimal payValue,
    LocalDateTime createdAt,
    LocalDate payDate,
    LocalDate dueDate,
    PaymentTypeEnum paymentType,
    PaymentStatusEnum paymentStatus
){
}
