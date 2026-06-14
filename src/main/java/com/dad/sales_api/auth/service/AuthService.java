package com.dad.sales_api.auth.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dad.sales_api.auth.dto.input.LoginInputDTO;
import com.dad.sales_api.auth.dto.output.LoginOutputDTO;
import com.dad.sales_api.shared.config.CustomUserDetails;
import com.dad.sales_api.shared.config.JwtUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserDetailsService userDetailsService;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtils jwtUtils;


  public LoginOutputDTO login(LoginInputDTO input) {
    System.out.println("ENTROU NO LOGIN");
    String email = input.email();
    String senha = input.password();

    UserDetails userDetails =
      userDetailsService.loadUserByUsername(email);

    CustomUserDetails user =
        (CustomUserDetails) userDetails;

    if (!passwordEncoder.matches(senha, userDetails.getPassword())) {
      return new LoginOutputDTO(
        "",
        HttpStatus.UNAUTHORIZED.value(),
        "Usuário e/ou senha inválidos"
      );
    }

    String token = jwtUtils.generateToken(
      user.getUsername(),
      user.getRole(),
      user.getId()
    );

    return new LoginOutputDTO(
      token,
      HttpStatus.OK.value(),
      "Login realizado com sucesso!"
    );
  }
}
