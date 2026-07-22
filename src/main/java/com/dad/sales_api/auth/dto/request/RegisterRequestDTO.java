package com.dad.sales_api.auth.dto.request;

import com.dad.sales_api.shared.helpers.RegexPatterns;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import com.dad.sales_api.shared.SalesConstants;

public record RegisterRequestDTO(

    @NotBlank(message = "{validation.name.required}")
    @Size(
        min = SalesConstants.MIN_NAME_LENGTH,
        max = SalesConstants.MAX_NAME_LENGTH,
        message ="{validation.name.size}"
    )
    @Pattern(
        regexp = RegexPatterns.NAME,
        message = "{validation.name.regex}"
    )
    String name,

    @NotBlank(message = "{validation.cpf.required}")
    @Pattern(
        regexp = RegexPatterns.CPF,
        message = "{validation.cpf.regex}"
    )
    String cpf,

    @NotBlank(message = "{validation.email.required}")
    @Email(message = "{validation.email.regex}")
    @Size(
        min = SalesConstants.MIN_EMAIL_LENGTH,
        max = SalesConstants.MAX_EMAIL_LENGTH,
        message = "{validation.email.size}"
    )
    String email,

    @NotBlank(message = "{validation.password.required}")
    @Size(
        min = SalesConstants.MIN_PASSWORD_LENGTH,
        max = SalesConstants.MAX_PASSWORD_LENGTH,
        message = "{validation.password.size}"
    )
    @Pattern(
        regexp = RegexPatterns.PASSWORD,
        message = "{validation.password.regex}"
    )
    String password
) {
    
}
