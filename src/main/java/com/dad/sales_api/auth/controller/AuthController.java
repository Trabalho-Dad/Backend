package com.dad.sales_api.auth.controller;

import com.dad.sales_api.auth.dto.input.*;
import com.dad.sales_api.auth.dto.output.ValidateCodeOutputDTO;
import com.dad.sales_api.auth.dto.request.ChangePasswordRequestDTO;
import com.dad.sales_api.shared.helpers.RegexPatterns;
import jakarta.mail.MessagingException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.dad.sales_api.auth.dto.output.LoginOutputDTO;
import com.dad.sales_api.auth.dto.output.RegisterOutputDTO;
import com.dad.sales_api.auth.dto.request.LoginRequestDTO;
import com.dad.sales_api.auth.dto.request.RegisterRequestDTO;
import com.dad.sales_api.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;
  
  @PostMapping("/login")
  public ResponseEntity<LoginOutputDTO> login(
    @RequestBody
    @Valid
    LoginRequestDTO input
  ) {
    LoginOutputDTO response = authService.login(new LoginInputDTO(input));

    return ResponseEntity
      .status(response.status())
      .body(response);
  }
  
  @PostMapping("/register")
  public ResponseEntity<RegisterOutputDTO> register(
      @RequestBody
      @Valid
      RegisterRequestDTO input
  ) {
    return new ResponseEntity(
        authService.register(
          new RegisterInputDTO(input)
        ),
        HttpStatus.CREATED
      );
  }

  @Validated
  @GetMapping("/send-code/{email}")
  public ResponseEntity<Void> sendCode(
      @PathVariable
      @Valid
      @NotBlank(message = "Email é obrigatório")
      @Email
      String email
  ) throws MessagingException {
    this.authService.sendCode(
        new SendCodeInputDTO(
            email
        )
    );

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Validated
  @GetMapping("/validate-code/{email}/{code}")
  public ResponseEntity<ValidateCodeOutputDTO> validateCode(
    @PathVariable
    @Valid
    @NotBlank(message = "Email é obrigatório.")
    @Email(message = "Email inválido")
    String email,

    @PathVariable
    @NotNull(message = "Código é obrigatório.")
    @Pattern(
        regexp = RegexPatterns.CODE,
        message = "O código deve ter 6 algarismos numéricos."
    )
    String code
  ){
    return new ResponseEntity<>(
        this.authService.validateCode(
            new ValidateCodeInputDTO(email, code)
        ),
        HttpStatus.OK
    );
  }

  @PostMapping("/change-password/{email}/{code}")
  public ResponseEntity<Void> changePassword(
    @PathVariable
    @Valid
    @NotBlank(message = "Email é obrigatório.")
    @Email(message = "Email inválido")
    String email,

    @PathVariable
    @NotNull(message = "Código é obrigatório.")
    @Pattern(
        regexp = RegexPatterns.CODE,
        message = "O código deve ter 6 algarismos numéricos."
    )
    String code,

    @RequestBody
    @Valid
    ChangePasswordRequestDTO input
  ){
    authService.changePassword(
        new ChangePasswordInputDTO(
            email,
            code,
            input.newPassword(),
            input.confirmPassword()
        )
    );

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}