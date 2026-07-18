package com.dad.sales_api.auth.controller;

import com.dad.sales_api.auth.dto.input.*;
import com.dad.sales_api.auth.dto.output.SendCodeOutputDTO;
import com.dad.sales_api.auth.dto.output.ValidateCodeOutputDTO;
import com.dad.sales_api.auth.dto.request.ChangePasswordRequestDTO;
import com.dad.sales_api.shared.helpers.RegexPatterns;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

@Tag(name = "Auth", description = "Autenticação de usuários.")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  @Operation(
      summary = "Realiza login",
      description = "Faz login de um usuário e retorna um JWT Token",
      tags = { "Auth" },
      responses = {
          @ApiResponse(description = "Success", responseCode = "200", content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              array = @ArraySchema(schema = @Schema(implementation = LoginOutputDTO.class))
          )
          ),
          @ApiResponse(description = "Unhautorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
          @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
      }
  )
  @PostMapping("/login")
  public ResponseEntity<LoginOutputDTO> login(
    @RequestBody
    @Valid
    LoginRequestDTO input
  ) {
    return new ResponseEntity<>(
        authService.login(new LoginInputDTO(input)),
        HttpStatus.OK
    );
  }

  @Operation(
      summary = "Cadastra um cliente",
      description = "Cadastra um novo cliente dentro do sistema",
      tags = { "Auth" },
      responses = {
          @ApiResponse(description = "Created", responseCode = "201", content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              array = @ArraySchema(schema = @Schema(implementation = RegisterOutputDTO.class))
          )
          ),
          @ApiResponse(description = "Unhautorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
          @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
      }
  )
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

  @Operation(
      summary = "Envia um código para recuperação de senha",
      description = "Envia um código para o email, a fim de validar a identidade da operação",
      tags = { "Auth" },
      responses = {
          @ApiResponse(description = "Success", responseCode = "200", content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              array = @ArraySchema(schema = @Schema(implementation = SendCodeOutputDTO.class))
          )
          ),
          @ApiResponse(description = "Unhautorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
          @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
      }
  )
  @Validated
  @GetMapping("/send-code/{email}")
  public ResponseEntity<SendCodeOutputDTO> sendCode(
      @PathVariable
      @Valid
      @NotBlank(message = "{validation.email.required}")
      @Email(message = "{validation.email.regex}")
      String email
  ) throws MessagingException {
    return new ResponseEntity<>(
        this.authService.sendCode(
            new SendCodeInputDTO(
                email
            )
        ),
        HttpStatus.OK
    );
  }

  @Operation(
      summary = "Valida o código enviado ao e-mail",
      description = "Valida se o código digitado está correto para este e-mail",
      tags = { "Auth" },
      responses = {
          @ApiResponse(description = "Success", responseCode = "200", content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              array = @ArraySchema(schema = @Schema(implementation = LoginOutputDTO.class))
          )
          ),
          @ApiResponse(description = "Unhautorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
          @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
      }
  )
  @Validated
  @GetMapping("/validate-code/{email}/{code}")
  public ResponseEntity<ValidateCodeOutputDTO> validateCode(
    @PathVariable
    @Valid
    @NotBlank(message = "{validation.email.required}")
    @Email(message = "{validation.email.regex}")
    String email,

    @PathVariable
    @NotBlank(message = "{validation.change-password.code.required}")
    @Pattern(
        regexp = RegexPatterns.CODE,
        message = "{validation.change-password.code.regex}"
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

  @Operation(
      summary = "Realiza a troca da senha",
      description = "Troca a senha do usuário após a confirmação da identidade",
      tags = { "Auth" },
      responses = {
          @ApiResponse(description = "No Content", responseCode = "204", content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              array = @ArraySchema(schema = @Schema(implementation = Void.class))
          )
          ),
          @ApiResponse(description = "Unhautorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
          @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
      }
  )
  @PostMapping("/change-password/{email}/{code}")
  public ResponseEntity<Void> changePassword(
    @PathVariable
    @Valid
    @NotBlank(message = "{validation.email.required}")
    @Email(message = "{validation.email.regex}")
    String email,

    @PathVariable
    @NotBlank(message = "{validation.change-password.code.required}")
    @Pattern(
        regexp = RegexPatterns.CODE,
        message = "{validation.change-password.code.regex}"
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