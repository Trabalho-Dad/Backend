package com.dad.sales_api.user.dto.request;

import com.dad.sales_api.shared.SalesConstants;
import com.dad.sales_api.shared.helpers.RegexPatterns;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateUserRequestDTO (
    @Size(min = SalesConstants.MIN_NAME_LENGTH, max = SalesConstants.MAX_NAME_LENGTH, message = "O nome deve ter entre "
        + SalesConstants.MIN_NAME_LENGTH + " e "
        + SalesConstants.MAX_NAME_LENGTH + " letras.")
    @Pattern(
        regexp = RegexPatterns.NAME,
        message = "O nome deve começar com letra maiúscula e conter apenas letras."
    )
    String name,

    @Email(message = "Formato de email inválido.")
    @Size(min = SalesConstants.MIN_EMAIL_LENGTH, max = SalesConstants.MAX_EMAIL_LENGTH, message = "O email deve ter entre "
        + SalesConstants.MIN_EMAIL_LENGTH + " e "
        + SalesConstants.MAX_EMAIL_LENGTH + " caracteres.")
    String email

    
){
}
