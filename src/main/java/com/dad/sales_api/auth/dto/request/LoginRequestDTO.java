package com.dad.sales_api.auth.dto.request;

import com.dad.sales_api.shared.utils.SalesConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDTO(
  @NotBlank
  @Email
  String email,

  @NotBlank
  @Size(min = SalesConstants.MIN_LENGTH_PASSWORD, max = SalesConstants.MAX_LENGTH_PASSWORD)
  String password
) {
  
}
