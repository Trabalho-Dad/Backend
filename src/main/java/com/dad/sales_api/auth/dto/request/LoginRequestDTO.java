package com.dad.sales_api.auth.dto.request;

import com.dad.sales_api.shared.SalesConstants;
import com.dad.sales_api.shared.helpers.RegexPatterns;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record LoginRequestDTO(
  @NotBlank(message = "{validation.email.required}")
  @Email(message = "{validation.email.regex}")
  @Size(
      min = SalesConstants.MIN_EMAIL_LENGTH,
      max = SalesConstants.MAX_EMAIL_LENGTH,
      message = "{login.invalid}"
  )
  String email,

  @NotBlank(message = "{validation.password.required}")
  @Size(
      min = SalesConstants.MIN_PASSWORD_LENGTH,
      max = SalesConstants.MAX_PASSWORD_LENGTH,
      message = "{exception.login.unhautorized}"
  )
  String password
) {
  
}
