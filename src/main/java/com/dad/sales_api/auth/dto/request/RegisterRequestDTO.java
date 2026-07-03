package com.dad.sales_api.auth.dto.request;

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
        regexp = "^[A-ZÀ-Ý][a-zA-ZÀ-ÿ]*(\\s[A-ZÀ-Ý][a-zA-ZÀ-ÿ]*)*$",
        message = "O nome deve começar com letra maiúscula e conter apenas letras."
    )
    String name,

    @NotBlank
    @Pattern(
        regexp = "^(\\d{11}|\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})$",
        message = "O CPF deve estar no formato 99999999999 ou 999.999.999-99"
    )
    String cpf,

    @NotBlank
    @Email
    String email,

    @NotBlank
    @Size(min = SalesConstants.MIN_PASSWORD_LENGTH, max = SalesConstants.MAX_PASSWORD_LENGTH, message = "A senha deve ter entre "
        + SalesConstants.MIN_NAME_LENGTH + " e "
        + SalesConstants.MAX_NAME_LENGTH + " caracteres."
    )
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$",
        message = "A senha deve conter pelo menos uma letra minúscula, uma maiúscula e um número"
    )
    String password
) {
    
}
