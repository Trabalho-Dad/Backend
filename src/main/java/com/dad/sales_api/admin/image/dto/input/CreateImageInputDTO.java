package com.dad.sales_api.admin.image.dto.input;

import com.dad.sales_api.admin.image.dto.request.CreateImageRequestDTO;
import com.dad.sales_api.shared.enums.ImageTypeEnum;
import com.dad.sales_api.shared.helpers.NormalizeInput;

public record CreateImageInputDTO(
  String url,
  String description,
  ImageTypeEnum imageType
) {
  public  CreateImageInputDTO(CreateImageRequestDTO input){
      this(
          NormalizeInput.url(input.url()),
          NormalizeInput.description(input.description()),
          input.imageType()
      );
  }
}
