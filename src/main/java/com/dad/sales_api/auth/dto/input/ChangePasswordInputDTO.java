package com.dad.sales_api.auth.dto.input;

public record ChangePasswordInputDTO(
    String email,
    Integer code,
    String newPassword,
    String confirmPassword
) {
}
