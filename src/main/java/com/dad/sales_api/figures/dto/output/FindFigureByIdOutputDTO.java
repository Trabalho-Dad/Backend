package com.dad.sales_api.figures.dto.output;

import java.math.BigDecimal;
import java.util.List;

import com.dad.sales_api.shared.dto.AccessorySimpleDTO;
import com.dad.sales_api.shared.dto.CategorySimpleDTO;
import com.dad.sales_api.shared.dto.CharacterSimpleDTO;

public record FindFigureByIdOutputDTO(
  Integer id,
  String name,
  String description,
  BigDecimal price,
  String imgUrl,
  Integer quantity,
  Boolean active,
  CharacterSimpleDTO character,
  List<AccessorySimpleDTO> accessories,
  List<CategorySimpleDTO> categories
) {
  
}
