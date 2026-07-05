package com.dad.sales_api.admin.figure.dto.output;

import java.math.BigDecimal;

import com.dad.sales_api.shared.helpers.NormalizeOutput;
import com.dad.sales_api.shared.persistence.postgres.entities.FigureEntity;

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
        NormalizeOutput.name(entity.getName()),
        entity.getActive(),
        oldQuantity,
        entity.getQuantity(),
        entity.getPrice()
    );
  }
}