package com.dad.sales_api.shared.dto;

import com.dad.sales_api.shared.utils.enums.ImageTypeEnum;

public record ImageSimpleDTO(
  Integer id,
  String description,
  String url,
  ImageTypeEnum imageType
) {
  
}
