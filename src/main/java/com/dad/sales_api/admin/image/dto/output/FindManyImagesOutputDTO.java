package com.dad.sales_api.admin.image.dto.output;

import com.dad.sales_api.shared.persistence.postgres.dto.ImageSimpleDTO;

import java.util.List;

public record FindManyImagesOutputDTO(
    List<ImageSimpleDTO> images,
    Integer totalPages,
    Integer count
) {
}
