package com.dad.sales_api.admin.image.dto.query_params;

import com.dad.sales_api.shared.enums.ImageTypeEnum;
import com.dad.sales_api.shared.helpers.NormalizeInput;
import jakarta.validation.constraints.Min;

public record FindManyImagesQueryParamsDTO(
    String description,
    ImageTypeEnum type,
    @Min(1)
    Integer page,

    @Min(1)
    Integer take
) {
  public FindManyImagesQueryParamsDTO(String description, ImageTypeEnum type, Integer page, Integer take) {
    this.description = description;
    this.type = type;
    this.page = page != null ? page : 1;
    this.take = take != null ? take : 4;
  }
}