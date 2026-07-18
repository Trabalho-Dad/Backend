package com.dad.sales_api.admin.character.dto.request;

import com.dad.sales_api.shared.helpers.RegexPatterns;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

import com.dad.sales_api.shared.SalesConstants;

public record CreateCharacterRequestDTO(
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
    Boolean active,
    List<Integer> imageIds
) {
  public CreateCharacterRequestDTO(String name, String description, Boolean active, List<Integer> imageIds) {
    this.name = name;
    this.description = description != null ? description : "";
    this.active = active != null ? active : true;
    this.imageIds = imageIds;
  }
}
