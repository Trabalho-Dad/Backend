package com.dad.sales_api.admin.character.dto.request;

import com.dad.sales_api.shared.SalesConstants;

import com.dad.sales_api.shared.helpers.RegexPatterns;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateCharacterRequestDTO(
    @NotBlank
    @Size(
        min = SalesConstants.MIN_NAME_LENGTH,
        max = SalesConstants.MAX_NAME_LENGTH,
        message = "O nome do personagem deve ter entre " +
            SalesConstants.MIN_NAME_LENGTH + " e " +
            SalesConstants.MAX_NAME_LENGTH + " caracteres"
    )
    @Pattern(
        regexp = RegexPatterns.NAME,
        message = "O nome deve conter apenas letras acentuadas ou não."
    )
    String name,
    String description,
    Boolean active
) {

}
