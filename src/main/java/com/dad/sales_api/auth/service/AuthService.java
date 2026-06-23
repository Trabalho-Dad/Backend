package com.dad.sales_api.auth.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dad.sales_api.auth.dto.input.LoginInputDTO;
import com.dad.sales_api.auth.dto.input.RegisterInputDTO;
import com.dad.sales_api.auth.dto.output.LoginOutputDTO;
import com.dad.sales_api.auth.dto.output.RegisterOutputDTO;
import com.dad.sales_api.shared.config.CustomUserDetails;
import com.dad.sales_api.shared.config.JwtUtils;
import com.dad.sales_api.shared.entities.CustomerEntity;
import com.dad.sales_api.shared.exceptions.ConflictException;
import com.dad.sales_api.shared.repositories.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserDetailsService userDetailsService;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtils jwtUtils;
  private final CustomerRepository customerRepository;


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

  public RegisterOutputDTO register(RegisterInputDTO input) {
    CustomerEntity customerExists =
        customerRepository.findByEmail(input.email());

    if (customerExists != null) throw new ConflictException(
       "Email já cadastrado"
      );

    CustomerEntity customer = new CustomerEntity();

    customer.setName(input.name());
    customer.setEmail(input.email());

    customer.setPassword(
      passwordEncoder.encode(input.password())
    );

    customerRepository.save(customer);

    return new RegisterOutputDTO(
      customer.getId(),
      customer.getName(),
      customer.getEmail()
    );
  }
}
