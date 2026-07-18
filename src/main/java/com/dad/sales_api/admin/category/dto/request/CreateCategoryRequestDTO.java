package com.dad.sales_api.admin.category.dto.request;

import com.dad.sales_api.shared.SalesConstants;

import com.dad.sales_api.shared.helpers.NormalizeInput;
import com.dad.sales_api.shared.helpers.RegexPatterns;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

public record CreateCategoryRequestDTO(
    @NotBlank(message = "{validation.name.required}")
    @Size(
        min = SalesConstants.MIN_NAME_LENGTH,
        max = SalesConstants.MAX_NAME_LENGTH,
        message = "{validation.name.size}"
    )
    @Pattern(
        regexp = RegexPatterns.NAME,
        message = "{validation.name.regex}"
    )
  String name,
  String description,
  Boolean active
) {
  public CreateCategoryRequestDTO(String name, String description, Boolean active){
    this.name = name;
    this.description = description != null ? description : "";
    this.active = active != null ? active : true;
  }
}