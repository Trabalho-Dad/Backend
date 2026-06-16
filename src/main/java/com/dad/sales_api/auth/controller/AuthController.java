package com.dad.sales_api.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dad.sales_api.auth.dto.input.LoginInputDTO;
import com.dad.sales_api.auth.dto.output.LoginOutputDTO;
import com.dad.sales_api.auth.dto.request.LoginRequestDTO;
import com.dad.sales_api.auth.service.AuthService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;
  
  @PostMapping("/login")
  public ResponseEntity<LoginOutputDTO> login(
    @RequestBody
    LoginRequestDTO input
  ) {
    LoginOutputDTO response = authService.login(new LoginInputDTO(input)); 
    return ResponseEntity
      .status(response.status())
      .body(response);
  }
}