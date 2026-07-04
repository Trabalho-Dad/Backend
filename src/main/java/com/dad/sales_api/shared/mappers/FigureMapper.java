package com.dad.sales_api.shared.mappers;

import com.dad.sales_api.shared.dto.FigureSimpleDTO;
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
      entity.getImages().stream().map(ImageMapper::convertEntityToSimpleDTO).toList()
    );
  }
}
