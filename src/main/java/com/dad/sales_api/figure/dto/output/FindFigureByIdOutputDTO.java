package com.dad.sales_api.figure.dto.output;

import java.math.BigDecimal;
import java.util.List;

import com.dad.sales_api.shared.persistence.postgres.dto.AccessorySimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.dto.CategorySimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.dto.CharacterSimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.dto.ImageSimpleDTO;

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
  
}
