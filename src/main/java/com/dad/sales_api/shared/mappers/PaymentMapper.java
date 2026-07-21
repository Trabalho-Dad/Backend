package com.dad.sales_api.shared.mappers;

import com.dad.sales_api.shared.persistence.postgres.dto.PaymentSimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.entities.PaymentEntity;

public class PaymentMapper {
  public static PaymentSimpleDTO convertEntityToSimpleDTO(PaymentEntity entity){
    return new PaymentSimpleDTO(
        entity.getId(),
        entity.getPayValue(),
        entity.getPayDate(),
        entity.getDueDate(),
        entity.getPaymentType()
    );
  }
}
