package com.dad.sales_api.auth.dto.request;

import com.dad.sales_api.shared.SalesConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ChangePasswordRequestDTO(
    @NotBlank
    @Size(min = SalesConstants.MIN_PASSWORD_LENGTH, max = SalesConstants.MAX_PASSWORD_LENGTH, message = "A senha deve ter entre "
        + SalesConstants.MIN_PASSWORD_LENGTH + " e "
        + SalesConstants.MAX_PASSWORD_LENGTH + " caracteres."
    )
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$",
        message = "A senha deve conter pelo menos uma letra minúscula, uma maiúscula e um número"
    )
    String newPassword,
    String confirmPassword
) {
}
