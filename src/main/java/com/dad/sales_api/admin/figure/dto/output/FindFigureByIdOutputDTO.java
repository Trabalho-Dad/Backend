package com.dad.sales_api.admin.figure.dto.output;

import java.math.BigDecimal;
import java.util.List;

import com.dad.sales_api.shared.persistence.postgres.dto.AccessorySimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.dto.CategorySimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.dto.CharacterSimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.dto.ImageSimpleDTO;
import com.dad.sales_api.shared.helpers.NormalizeOutput;

public record FindFigureByIdOutputDTO(
  Integer id,
  String name,
  String description,
  BigDecimal price,
  Integer quantity,
  Boolean active,
  Boolean isLaunch,
  CharacterSimpleDTO character,
  List<AccessorySimpleDTO> accessories,
  List<CategorySimpleDTO> categories,
  List<ImageSimpleDTO> images
) {
  public FindFigureByIdOutputDTO(Integer id, String name, String description, BigDecimal price, Integer quantity, Boolean active, Boolean isLaunch, CharacterSimpleDTO character, List<AccessorySimpleDTO> accessories, List<CategorySimpleDTO> categories, List<ImageSimpleDTO> images) {
    this.id = id;
    this.name = NormalizeOutput.name(name);
    this.description = description;
    this.price = price;
    this.quantity = quantity;
    this.active = active;
    this.isLaunch = isLaunch;
    this.character = character;
    this.accessories = accessories;
    this.categories = categories;
    this.images = images;
  }
}