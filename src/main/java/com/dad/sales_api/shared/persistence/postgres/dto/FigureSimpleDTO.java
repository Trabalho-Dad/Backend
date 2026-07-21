package com.dad.sales_api.shared.persistence.postgres.dto;

import com.dad.sales_api.shared.helpers.NormalizeOutput;

import java.math.BigDecimal;
import java.util.List;

public record FigureSimpleDTO(
  Integer id,
  String name,
  String description,
  BigDecimal price,
  Integer quantity,
  Boolean active,
  Boolean isLaunch,
  String category,
  List<ImageSimpleDTO> images
) {
  public FigureSimpleDTO(Integer id, String name, String description, BigDecimal price, Integer quantity, Boolean active, Boolean isLaunch, String category, List<ImageSimpleDTO> images) {
    this.id = id;
    this.name = NormalizeOutput.name(name);
    this.description = description;
    this.price = price;
    this.quantity = quantity;
    this.active = active;
    this.isLaunch = isLaunch;
    this.category = category;
    this.images = images;
  }
}
