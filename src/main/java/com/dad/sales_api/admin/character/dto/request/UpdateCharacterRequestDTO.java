package com.dad.sales_api.admin.character.dto.request;

import com.dad.sales_api.shared.SalesConstants;

import com.dad.sales_api.shared.helpers.RegexPatterns;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateCharacterRequestDTO(
    @NotBlank(message = "{validation.name.required}")
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
    String description,
    Boolean active
) {

}
