package com.dad.sales_api.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import com.dad.sales_api.shared.utils.SalesConstants;;

public record RegisterRequestDTO(

    @NotBlank
    @Size(min = SalesConstants.MIN_LENGTH_NAME, max = SalesConstants.MAX_LENGTH_NAME, message = "O nome deve ter entre " 
        + SalesConstants.MIN_LENGTH_NAME + " e "
        + SalesConstants.MAX_LENGTH_NAME + " letras.")
    @Pattern(
        regexp = "^[A-ZÀ-Ý][a-zA-ZÀ-ÿ]*(\\s[A-ZÀ-Ý][a-zA-ZÀ-ÿ]*)*$",
        message = "O nome deve começar com letra maiúscula e conter apenas letras."
    )
    String name,

    @NotBlank
    @Email
    String email,

    @NotBlank
    @Size(min = SalesConstants.MIN_LENGTH_PASSWORD, max = SalesConstants.MAX_LENGTH_PASSWORD, message = "A senha deve ter entre " 
        + SalesConstants.MIN_LENGTH_NAME + " e "
        + SalesConstants.MAX_LENGTH_NAME + " caracteres."
    )
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$",
        message = "A senha deve conter pelo menos uma letra minúscula, uma maiúscula e um número"
    )
    String password
) {
    
}
