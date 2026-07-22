package com.dad.sales_api.shared.mappers;

import com.dad.sales_api.shared.persistence.postgres.dto.UserOrderSimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.entities.UserOrderEntity;

public class UserOrderMapper {
  public static UserOrderSimpleDTO convertEntityToSimpleDTO(UserOrderEntity userOrder){
    return new UserOrderSimpleDTO(
        userOrder.getId(),
        userOrder.getPrice(),
        userOrder.getFinalPrice(),
        userOrder.getDiscount(),
        userOrder.getStatus()
    );
  }
}
