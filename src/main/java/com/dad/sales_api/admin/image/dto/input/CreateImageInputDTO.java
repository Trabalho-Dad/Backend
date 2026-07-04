package com.dad.sales_api.admin.image.dto.input;

import com.dad.sales_api.admin.image.dto.request.CreateImageRequestDTO;
import com.dad.sales_api.shared.enums.ImageTypeEnum;

public record CreateImageInputDTO(
  String url,
  String description,
  ImageTypeEnum imageType
) {
  public  CreateImageInputDTO(CreateImageRequestDTO input){
      this(
          input.url(),
          input.description(),
          input.imageType()
      );
  }
}
