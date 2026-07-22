package com.dad.sales_api.user.controller;

import com.dad.sales_api.shared.config.CustomUserDetails;
import com.dad.sales_api.user.dto.input.FindMyUserInputDTO;
import com.dad.sales_api.user.dto.input.UpdateUserInputDTO;
import com.dad.sales_api.user.dto.output.FindMyUserOutputDTO;
import com.dad.sales_api.user.dto.output.UpdateUserOutputDTO;
import com.dad.sales_api.user.dto.request.UpdateUserRequestDTO;
import com.dad.sales_api.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController("publicUserController")
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Tag(name = "User", description = "Rotas para visualização e edição do usuário logado")
public class UserController {
  private final UserService userService;

  @GetMapping
  @Operation(
      summary = "Retorna o meu usuário",
      description = "Retorna todos as informações do meu usuário",
      tags = {"User"},
      responses = {
          @ApiResponse(description = "Success", responseCode = "200", content =
            @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                array = @ArraySchema(schema = @Schema(implementation = FindMyUserOutputDTO.class))
            )
          ),
          @ApiResponse(description = "Unhautorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
          @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
      }
  )
  public ResponseEntity<FindMyUserOutputDTO> findMyUser(
      Authentication authentication
  ){
    CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

    return new ResponseEntity<>(
        this.userService.findMyUser(
            new FindMyUserInputDTO(
                user.getId()
            )
        ),
        HttpStatus.OK
    );
  }

  @Operation(
      summary = "Atualiza informações do meu usuário",
      description = "Altera nome e/ou e-mail do meu usuário",
      tags = {"User"},
      responses = {
          @ApiResponse(description = "Success", responseCode = "200", content =
            @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                array = @ArraySchema(schema = @Schema(implementation = UpdateUserOutputDTO.class))
            )
          ),
          @ApiResponse(description = "Unhautorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
          @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
      }
  )
  @PutMapping
  public ResponseEntity<UpdateUserOutputDTO> update(
      Authentication authentication,

      @RequestBody
      @Valid
      UpdateUserRequestDTO input
  ){
    CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

    return new ResponseEntity(
        this.userService.update(
            new UpdateUserInputDTO(
                user.getId(),
                input
            )
        ),
        HttpStatus.OK
    );
  }
}
