package com.dad.sales_api.admin.image.dto.request;

import com.dad.sales_api.shared.SalesConstants;
import com.dad.sales_api.shared.enums.ImageTypeEnum;
import com.dad.sales_api.shared.helpers.RegexPatterns;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record CreateImageRequestDTO (
    @NotBlank(message = "A descrição da imagem não pode ser nula")
    String description,

    @NotBlank
    @Size(min = SalesConstants.MIN_URL_LENGTH, max = SalesConstants.MAX_URL_LENGTH, message = "A url deve ter entre "
        + SalesConstants.MIN_URL_LENGTH + " e "
        + SalesConstants.MAX_URL_LENGTH + " caracteres."
    )
    @URL(
        protocol = "https",
        message = "Informe uma URL HTTPS válida. Exemplo: https://exemplo.com/algo"
    )
    String url,

    @NotNull(message = "O tipo da imagem é obrigatório")
    ImageTypeEnum imageType
){
}
