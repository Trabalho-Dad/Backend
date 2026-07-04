package com.dad.sales_api.admin.character.dto.input;

import java.util.List;

import com.dad.sales_api.admin.character.dto.request.CreateCharacterRequestDTO;

public record CreateCharacterInputDTO(
  String name,
  String description,
  Boolean active,
  List<Integer> imageIds
) {
  public CreateCharacterInputDTO(CreateCharacterRequestDTO input){
    this(input.name(), input.description(), input.active(), input.imageIds());
  }
}
