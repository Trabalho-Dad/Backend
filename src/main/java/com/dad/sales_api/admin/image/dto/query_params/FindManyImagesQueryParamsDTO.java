package com.dad.sales_api.admin.image.dto.query_params;

import com.dad.sales_api.shared.enums.ImageTypeEnum;
import com.dad.sales_api.shared.helpers.NormalizeInput;
import jakarta.validation.constraints.Min;

public record FindManyImagesQueryParamsDTO(
    String description,
    ImageTypeEnum type,

    @Min(value = 1, message = "{validation.page.min-value}")
    Integer page,

    @Min(value = 1, message = "{validation.take.min-value}")
    Integer take
) {
  public FindManyImagesQueryParamsDTO(String description, ImageTypeEnum type, Integer page, Integer take) {
    this.description = description;
    this.type = type;
    this.page = page != null ? page : 1;
    this.take = take != null ? take : 4;
  }
}