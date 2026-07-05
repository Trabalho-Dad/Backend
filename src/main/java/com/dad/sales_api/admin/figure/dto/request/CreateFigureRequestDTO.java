package com.dad.sales_api.admin.figure.dto.request;

import java.math.BigDecimal;
import java.util.List;

import com.dad.sales_api.shared.SalesConstants;

import com.dad.sales_api.shared.helpers.RegexPatterns;
import jakarta.validation.constraints.*;

public record CreateFigureRequestDTO(
  @NotBlank(message = "O nome é obrigatório")
  @Size(
      min = SalesConstants.MIN_NAME_LENGTH,
      max = SalesConstants.MAX_NAME_LENGTH,
      message = "O nome do boneco deve ter entre " +
          SalesConstants.MIN_NAME_LENGTH + " e " +
          SalesConstants.MAX_NAME_LENGTH + " caracteres"
  )
  @Pattern(
      regexp = RegexPatterns.NAME,
      message = "O nome deve conter apenas letras acentuadas ou não."
  )
  String name,
  
  String description,

  @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero.")
  BigDecimal price,

  @NotNull(message = "A quantidade é obrigatória.")
  @PositiveOrZero(message = "A quantidade não pode ser negativa.")
  Integer quantity,

  Boolean active,

  @NotNull(message = "O id personagem é obrigatório.")
  @Positive(message = "O id do personagem deve ser maior que zero.")
  Integer characterId,

  @NotEmpty(message = "É necessário informar ao menos um acessório.")
  List<@Positive(message = "Os ids dos acessórios devem ser maiores que zero.") Integer> accessoryIds,

  @NotEmpty(message = "É necessário informar ao menos uma categoria.")
  List<@Positive(message = "Os ids das categorias devem ser maiores que zero.") Integer> categoryIds,

  @NotEmpty(message = "É necessário informar ao menos uma imagem.")
  List<@Positive(message = "Os ids das imagens devem ser maiores que zero.") Integer> imageIds
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