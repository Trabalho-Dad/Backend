package com.dad.sales_api.auth.dto.request;

import com.dad.sales_api.shared.SalesConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDTO(
  @NotBlank
  @Email
  String email,

  @NotBlank
  @Size(min = SalesConstants.MIN_PASSWORD_LENGTH, max = SalesConstants.MAX_PASSWORD_LENGTH, message = "A senha deve ter entre "
      + SalesConstants.MIN_PASSWORD_LENGTH + " e "
      + SalesConstants.MAX_PASSWORD_LENGTH + " caracteres."
  )
  String password
) {
  
}
