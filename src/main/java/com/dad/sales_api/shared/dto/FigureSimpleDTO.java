package com.dad.sales_api.shared.dto;

import java.math.BigDecimal;

public record FigureSimpleDTO(
  Integer id,
  String name,
  String description,
  BigDecimal price,
  String imgUrl,
  Integer quantity,
  Boolean active
) {
  
}
