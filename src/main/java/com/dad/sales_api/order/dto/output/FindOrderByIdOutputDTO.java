package com.dad.sales_api.order.dto.output;

import com.dad.sales_api.shared.enums.OrderStatusEnum;
import com.dad.sales_api.shared.persistence.postgres.dto.PaymentSimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.dto.UserOrderFigureSimpleDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record FindOrderByIdOutputDTO(
    Integer id,
    BigDecimal price,
    BigDecimal discount,
    BigDecimal finalPrice,
    OrderStatusEnum status,
    LocalDateTime createdAt,
    List<UserOrderFigureSimpleDTO> figures,
    List<PaymentSimpleDTO> payments
) {
}
