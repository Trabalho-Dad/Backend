package com.dad.sales_api.shared.mappers;

import com.dad.sales_api.shared.dto.ImageSimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.entities.ImageEntity;

public class ImageMapper {
  public static ImageSimpleDTO convertEntityToSimpleDTO(ImageEntity entity){
    return new ImageSimpleDTO(
      entity.getId(),
      entity.getDescription(),
      entity.getUrl(),
      entity.getImageType()
    );
  }
}