package com.dad.sales_api.admin.image.dto.output;

import com.dad.sales_api.shared.utils.enums.ImageTypeEnum;

public record CreateImageOutputDTO(
  Integer id,
  String description,
  String url,
  ImageTypeEnum imageType
) {
  
}
