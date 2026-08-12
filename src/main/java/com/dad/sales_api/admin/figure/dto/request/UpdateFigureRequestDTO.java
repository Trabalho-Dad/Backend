package com.dad.sales_api.admin.figure.dto.request;

import com.dad.sales_api.admin.image.dto.request.CreateImageRequestDTO;
import com.dad.sales_api.shared.SalesConstants;
import com.dad.sales_api.shared.helpers.RegexPatterns;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

public record UpdateFigureRequestDTO(
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

  @Size(
      min = SalesConstants.MIN_DESCRIPTION_LENGTH,
      max = SalesConstants.MAX_DESCRIPTION_LENGTH,
      message = "{validation.description.size}"
  )
  String description,

  @DecimalMin(value = "0.01", message = "{validation.price.min-value}")
  BigDecimal price,

  @PositiveOrZero(message = "{validation.quantity.min-value}")
  Integer quantity,

  Boolean active,

  @NotEmpty(message = "{validation.list.categories.required}")
  List<@Positive(message = "{validation.list.categories.min-value}") Integer> categoryIds
) {
  public UpdateFigureRequestDTO(String name, String description, BigDecimal price, Integer quantity, Boolean active, List<Integer> categoryIds){
    this.name = name;
    this.description = description;
    this.price = price;
    this.quantity = quantity;
    this.active = active != null ? active : false;
    this.categoryIds = categoryIds;
  }
}