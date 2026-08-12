package com.dad.sales_api.admin.figure.dto.output;

import com.dad.sales_api.shared.helpers.NormalizeOutput;
import com.dad.sales_api.shared.mappers.AccessoryMapper;
import com.dad.sales_api.shared.mappers.CategoryMapper;
import com.dad.sales_api.shared.mappers.ImageMapper;
import com.dad.sales_api.shared.persistence.postgres.dto.AccessorySimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.dto.CategorySimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.dto.CharacterSimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.dto.ImageSimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.entities.FigureEntity;

import java.math.BigDecimal;
import java.util.List;

public record UpdateFigureOutputDTO(
    Integer id,
    String name,
    String description,
    BigDecimal price,
    Integer quantity,
    Boolean active,
    Boolean isLaunch,
    CharacterSimpleDTO character,
    List<CategorySimpleDTO> categories,
    List<AccessorySimpleDTO> acessories,
    List<ImageSimpleDTO> images
) {
  public UpdateFigureOutputDTO(FigureEntity entity) {
    this(
        entity.getId(),
        NormalizeOutput.name(entity.getName()),
        entity.getDescription(),
        entity.getPrice(),
        entity.getQuantity(),
        entity.getActive(),
        entity.getIsLaunch(),
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