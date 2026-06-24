package com.dad.sales_api.admin.figure.dto.output;

import java.math.BigDecimal;

import com.dad.sales_api.shared.entities.FigureEntity;

public record IncreaseOrDecreaseQuantityOutputDTO(
  Integer id,
  String name,
  Boolean active,
  Integer oldQuantity,
  Integer newQuantity,
  BigDecimal price
) {
  public IncreaseOrDecreaseQuantityOutputDTO(FigureEntity entity, Integer oldQuantity) {
    this(
      entity.getId(),
      entity.getName(),
      entity.getActive(),
      oldQuantity,
      entity.getQuantity(),
      entity.getPrice()
    );
  }
}