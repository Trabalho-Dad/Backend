package com.dad.sales_api.shared.mappers;

import com.dad.sales_api.shared.persistence.postgres.dto.UserOrderFigureSimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.entities.UserOrderFigureEntity;

public class UserOrderFigureMapper {
  public static UserOrderFigureSimpleDTO convertEntityToSimpleDTO(UserOrderFigureEntity entity){
    return new UserOrderFigureSimpleDTO(
        entity.getId(),
        FigureMapper.convertEntityToSimpleDTO(entity.getFigure()),
        entity.getQuantity(),
        entity.getPrice()
    );
  }
}
