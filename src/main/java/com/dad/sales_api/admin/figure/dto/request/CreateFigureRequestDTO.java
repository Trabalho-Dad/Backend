package com.dad.sales_api.admin.figure.dto.request;

import java.math.BigDecimal;
import java.util.List;

import com.dad.sales_api.shared.SalesConstants;

import com.dad.sales_api.shared.helpers.RegexPatterns;
import jakarta.validation.constraints.*;

public record CreateFigureRequestDTO(
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

  @NotNull(message = "{validation.price.required}")
  @DecimalMin(value = "0.01", message = "{validation.price.min-value}")
  BigDecimal price,

  @NotNull(message = "{validation.quantity.required}")
  @PositiveOrZero(message = "{validation.quantity.min-value}")
  Integer quantity,

  Boolean active,

  @NotNull(message = "{validation.character-id.required}")
  @Positive(message = "{validation.character-id.min-value}")
  Integer characterId,

  @NotEmpty(message = "{validation.list.accessories.required}")
  List<@Positive(message = "{validation.list.accessories.min-value}") Integer> accessoryIds,

  @NotEmpty(message = "{validation.list.categories.required}")
  List<@Positive(message = "{validation.list.categories.min-value}") Integer> categoryIds,

  @NotEmpty(message = "{validation.list.images.required}")
  List<@Positive(message = "{validation.list.images.min-value}") Integer> imageIds
) {
  public CreateFigureRequestDTO(String name, String description, BigDecimal price, Integer quantity, Boolean active, Integer characterId, List<Integer> accessoryIds, List<Integer> categoryIds, List<Integer> imageIds){
    this.name = name;
    this.description = description;
    this.price = price;
    this.quantity = quantity;
    this.active = active != null ? active : false;
    this.characterId = characterId;
    this.accessoryIds = accessoryIds;
    this.categoryIds = categoryIds;
    this.imageIds = imageIds;
  }
}