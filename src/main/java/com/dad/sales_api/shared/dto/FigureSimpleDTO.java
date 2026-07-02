package com.dad.sales_api.shared.dto;

import java.math.BigDecimal;
import java.util.List;

public record FigureSimpleDTO(
  Integer id,
  String name,
  String description,
  BigDecimal price,
  Integer quantity,
  Boolean active,
  List<ImageSimpleDTO> images
) {
  
}
