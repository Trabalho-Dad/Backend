package com.dad.sales_api.shared.mappers;

import com.dad.sales_api.shared.enums.ImageTypeEnum;
import com.dad.sales_api.shared.persistence.postgres.dto.FigureSimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.entities.FigureEntity;

public class FigureMapper {
  public static FigureSimpleDTO convertEntityToSimpleDTO(FigureEntity entity){
    return new FigureSimpleDTO(
      entity.getId(),
      entity.getName(),
      entity.getDescription(),
      entity.getPrice(),
      entity.getQuantity(),
      entity.getActive(),
      entity.getIsLaunch(),
      entity.getCategories().get(0).getName(),
      entity.getImages().stream()
        .filter(image -> image.getImageType() == ImageTypeEnum.PRIMARY_FIGURE)
        .findFirst()
        .map(ImageMapper::convertEntityToSimpleDTO)
        .orElse(null)
    );
  }
}
