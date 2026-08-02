package com.dad.sales_api.auth.dto.request;

import com.dad.sales_api.shared.SalesConstants;
import com.dad.sales_api.shared.helpers.RegexPatterns;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record LoginRequestDTO(
  String email,
  String password
) {
  
}
