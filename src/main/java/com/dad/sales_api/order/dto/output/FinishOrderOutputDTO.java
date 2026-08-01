package com.dad.sales_api.order.dto.output;

import com.dad.sales_api.shared.enums.OrderStatusEnum;
import com.dad.sales_api.shared.persistence.postgres.dto.AddressSimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.dto.PaymentSimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.dto.UserOrderFigureSimpleDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record FinishOrderOutputDTO (
    Integer id,
    BigDecimal price,
    BigDecimal shippingCost,
    BigDecimal discount,
    BigDecimal finalPrice,
    Integer estimatedDeliveryTime,
    Integer installmentsCount,
    OrderStatusEnum status,
    LocalDateTime createdAt,
    AddressSimpleDTO address,
    List<UserOrderFigureSimpleDTO> figures,
    PaymentSimpleDTO nextPayment
){
}
