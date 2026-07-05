package com.dad.sales_api.auth.dto.input;

import com.dad.sales_api.shared.helpers.NormalizeInput;

public record ChangePasswordInputDTO(
    String email,
    String code,
    String newPassword,
    String confirmPassword
) {
  public ChangePasswordInputDTO(String email, String code, String newPassword, String confirmPassword) {
    this.email = NormalizeInput.email(email);
    this.code = code;
    this.newPassword = newPassword;
    this.confirmPassword = confirmPassword;
  }
}
