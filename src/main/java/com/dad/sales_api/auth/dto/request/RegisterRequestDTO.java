package com.dad.sales_api.auth.dto.request;

import com.dad.sales_api.shared.helpers.RegexPatterns;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import com.dad.sales_api.shared.SalesConstants;;

public record RegisterRequestDTO(

    @NotBlank
    @Size(min = SalesConstants.MIN_NAME_LENGTH, max = SalesConstants.MAX_NAME_LENGTH, message = "O nome deve ter entre "
        + SalesConstants.MIN_NAME_LENGTH + " e "
        + SalesConstants.MAX_NAME_LENGTH + " letras.")
    @Pattern(
        regexp = RegexPatterns.NAME,
        message = "O nome deve começar com letra maiúscula e conter apenas letras."
    )
    String name,

    @NotBlank
    @Pattern(
        regexp = RegexPatterns.CPF,
        message = "O CPF deve estar no formato 99999999999 ou 999.999.999-99"
    )
    String cpf,

    @NotBlank
    @Email(message = "Formato de email inválido.")
    @Size(min = SalesConstants.MIN_EMAIL_LENGTH, max = SalesConstants.MAX_EMAIL_LENGTH, message = "O email deve ter entre "
        + SalesConstants.MIN_EMAIL_LENGTH + " e "
        + SalesConstants.MAX_EMAIL_LENGTH + " caracteres.")
    String email,

    @NotBlank
    @Size(min = SalesConstants.MIN_PASSWORD_LENGTH, max = SalesConstants.MAX_PASSWORD_LENGTH, message = "A senha deve ter entre "
        + SalesConstants.MIN_NAME_LENGTH + " e "
        + SalesConstants.MAX_NAME_LENGTH + " caracteres."
    )
    @Pattern(
        regexp = RegexPatterns.PASSWORD,
        message = "A senha deve conter pelo menos uma letra minúscula, uma maiúscula e um número"
    )
    String password
) {
    
}
