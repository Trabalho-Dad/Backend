package com.dad.sales_api.admin.image.dto.request;

import com.dad.sales_api.shared.SalesConstants;
import com.dad.sales_api.shared.enums.ImageTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record CreateImageRequestDTO (
    @NotBlank(message = "{validation.description.required}")
    @Size(
        min = SalesConstants.MIN_DESCRIPTION_LENGTH,
        max = SalesConstants.MAX_DESCRIPTION_LENGTH,
        message = "{validation.description.size}"
    )
    String description,

    @NotBlank(message = "{validation.url.required}")
    @Size(
        min = SalesConstants.MIN_URL_LENGTH,
        max = SalesConstants.MAX_URL_LENGTH,
        message = "{validation.url.size}"
    )
    @URL(
        protocol = "https",
        message = "{validation.url.regex}"
    )
    String url,

    @NotNull(message = "{validation.image-type.required}")
    ImageTypeEnum imageType
){
}
