package com.dad.sales_api.admin.image.dto.request;

import com.dad.sales_api.shared.SalesConstants;
import com.dad.sales_api.shared.enums.ImageTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateImageRequestDTO (
    @NotBlank(message = "A descrição da imagem não pode ser nula")
    String description,

    @NotBlank
    @Size(min = SalesConstants.MIN_URL_LENGTH, max = SalesConstants.MAX_URL_LENGTH, message = "A url deve ter entre "
        + SalesConstants.MIN_URL_LENGTH + " e "
        + SalesConstants.MAX_URL_LENGTH + " caracteres."
    )
    String url,

    @NotNull(message = "O tipo da imagem é obrigatório")
    ImageTypeEnum imageType
){
}
