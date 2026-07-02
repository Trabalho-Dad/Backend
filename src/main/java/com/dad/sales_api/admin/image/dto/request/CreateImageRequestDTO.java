package com.dad.sales_api.admin.image.dto.request;

import com.dad.sales_api.shared.utils.SalesConstants;
import com.dad.sales_api.shared.utils.enums.ImageTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateImageRequestDTO (
    @NotBlank(message = "A descrição da imagem não pode ser nula")
    String description,

    @NotBlank
    @Size(min = SalesConstants.MIN_LENGTH_URL, max = SalesConstants.MAX_LENGTH_URL, message = "A url deve ter entre "
        + SalesConstants.MIN_LENGTH_URL + " e "
        + SalesConstants.MAX_LENGTH_URL + " caracteres."
    )
    String url,

    @NotNull(message = "O tipo da imagem é obrigatório")
    ImageTypeEnum imageType
){
}
