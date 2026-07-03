package com.dad.sales_api.admin.figure.dto.output;

import java.math.BigDecimal;
import java.util.List;

import com.dad.sales_api.shared.dto.AccessorySimpleDTO;
import com.dad.sales_api.shared.dto.CategorySimpleDTO;
import com.dad.sales_api.shared.dto.CharacterSimpleDTO;
import com.dad.sales_api.shared.dto.ImageSimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.entities.FigureEntity;
import com.dad.sales_api.shared.mappers.AccessoryMapper;
import com.dad.sales_api.shared.mappers.CategoryMapper;
import com.dad.sales_api.shared.mappers.ImageMapper;

public record CreateFigureOutputDTO(
  Integer id,
  String name,
  String description,
  BigDecimal price,
  Integer quantity,
  Boolean active,
  CharacterSimpleDTO character,
  List<CategorySimpleDTO> categories,
  List<AccessorySimpleDTO> acessories,
  List<ImageSimpleDTO> images
) {
  public CreateFigureOutputDTO(FigureEntity entity){
    this(
      entity.getId(),
      entity.getName(),
      entity.getDescription(),
      entity.getPrice(),
      entity.getQuantity(),
      entity.getActive(),
      new CharacterSimpleDTO(entity.getCharacter()),
      entity.getCategories()
        .stream()
        .map(CategoryMapper::convertEntityToSimpleDTO)
        .toList(),
      entity.getAccessories()
        .stream()
        .map(AccessoryMapper::convertToSimpleDTO)
        .toList(),
      entity.getImages()
        .stream()
        .map(ImageMapper::convertEntityToSimpleDTO)
        .toList()
    );
  }
}