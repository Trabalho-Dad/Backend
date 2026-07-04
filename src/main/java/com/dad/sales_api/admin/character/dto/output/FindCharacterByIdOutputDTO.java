package com.dad.sales_api.admin.character.dto.output;

import java.util.List;

import com.dad.sales_api.shared.dto.FigureSimpleDTO;
import com.dad.sales_api.shared.dto.ImageSimpleDTO;

public record FindCharacterByIdOutputDTO(
  Integer id,
  String name,
  String description,
  Boolean active,
  List<FigureSimpleDTO> figures,
  List<ImageSimpleDTO> images
) {
  
}
