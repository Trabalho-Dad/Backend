package com.dad.sales_api.admin.figure.dto.request;

import java.math.BigDecimal;
import java.util.List;

import com.dad.sales_api.shared.utils.SalesConstants;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record CreateFigureRequestDTO(
  @NotBlank(message = "O nome é obrigatório")
  @Size(
    min = SalesConstants.MIN_LENGTH_NAME,
    max = SalesConstants.MAX_LENGTH_NAME,
    message = "O nome deve ter entre " +
      SalesConstants.MIN_LENGTH_NAME + " e " +
      SalesConstants.MAX_LENGTH_NAME + " caracteres"
  )
  String name,

  String description,

  @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero")
  BigDecimal price,

  @NotBlank(message = "A URL da imagem é obrigatória")
  String imgUrl,

  @NotNull(message = "A quantidade é obrigatória")
  @PositiveOrZero(message = "A quantidade não pode ser negativa")
  Integer quantity,

  Boolean active,

  @NotNull(message = "O id personagem é obrigatório")
  @Positive(message = "O id do personagem deve ser maior que zero")
  Integer characterId,

  @NotEmpty(message = "É necessário informar ao menos um acessório")
  List<@Positive(message = "Os ids dos acessórios devem ser maiores que zero") Integer> accessoryIds,

  @NotEmpty(message = "É necessário informar ao menos uma categoria")
  List<@Positive(message = "Os ids das categorias devem ser maiores que zero") Integer> categoryIds
) {
  public CreateFigureRequestDTO(String name, String description, BigDecimal price, String imgUrl, Integer quantity, Boolean active, Integer characterId, List<Integer> accessoryIds, List<Integer> categoryIds){
    this.name = name;
    this.description = description;
    this.price = price;
    this.imgUrl = imgUrl;
    this.quantity = quantity;
    this.active = active != null ? active : false;
    this.characterId = characterId;
    this.accessoryIds = accessoryIds;
    this.categoryIds = categoryIds;
  }
}