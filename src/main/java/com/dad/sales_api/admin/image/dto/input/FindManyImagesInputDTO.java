package com.dad.sales_api.admin.image.dto.input;

import com.dad.sales_api.admin.image.dto.query_params.FindManyImagesQueryParamsDTO;
import com.dad.sales_api.shared.enums.ImageTypeEnum;
import com.dad.sales_api.shared.helpers.NormalizeInput;

public record FindManyImagesInputDTO(
    String description,
    ImageTypeEnum type,
    Integer page,
    Integer take
) {
  public FindManyImagesInputDTO(FindManyImagesQueryParamsDTO query){
    this(
        NormalizeInput.description(query.description()),
        query.type(),
        query.page(),
        query.take()
    );
  }
}
