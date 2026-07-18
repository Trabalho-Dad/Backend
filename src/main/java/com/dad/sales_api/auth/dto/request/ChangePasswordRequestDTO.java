package com.dad.sales_api.auth.dto.request;

import com.dad.sales_api.shared.SalesConstants;
import com.dad.sales_api.shared.helpers.RegexPatterns;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ChangePasswordRequestDTO(
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
    String newPassword,
    @NotBlank(message = "{validation.change-password.required}")
    String confirmPassword
) {
}
