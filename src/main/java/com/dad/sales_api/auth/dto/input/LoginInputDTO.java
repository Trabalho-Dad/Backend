package com.dad.sales_api.auth.dto.input;

import com.dad.sales_api.auth.dto.request.LoginRequestDTO;

public record LoginInputDTO(
  String email,
  String password
) {
  public LoginInputDTO(LoginRequestDTO input){
    this(input.email(), input.password());
  }
}
