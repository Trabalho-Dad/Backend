package com.dad.sales_api.admin.image.dto.input;

import com.dad.sales_api.admin.image.dto.query_params.FindManyImagesQueryParamsDTO;
import com.dad.sales_api.shared.utils.enums.ImageTypeEnum;

public record FindManyImagesInputDTO(
    String description,
    ImageTypeEnum type,
    Integer page,
    Integer take
) {
  public FindManyImagesInputDTO(FindManyImagesQueryParamsDTO query){
    this(query.description(), query.type(), query.page(), query.take());
  }
}
