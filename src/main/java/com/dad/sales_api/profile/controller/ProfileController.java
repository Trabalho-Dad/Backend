package com.dad.sales_api.profile.controller;

import com.dad.sales_api.shared.config.CustomUserDetails;
import com.dad.sales_api.profile.dto.input.FindMyProfileInputDTO;
import com.dad.sales_api.profile.dto.input.UpdateProfileInputDTO;
import com.dad.sales_api.profile.dto.output.FindMyProfileOutputDTO;
import com.dad.sales_api.profile.dto.output.UpdateProfileOutputDTO;
import com.dad.sales_api.profile.dto.request.UpdateProfileRequestDTO;
import com.dad.sales_api.profile.service.ProfileService;
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
@RequestMapping("/api/profile")
@Tag(name = "Profile", description = "Rotas para visualização e edição do usuário logado")
public class ProfileController {
  private final ProfileService profileService;

  @GetMapping("/me")
  @Operation(
      summary = "Retorna o meu usuário",
      description = "Retorna todos as informações do meu usuário",
      tags = {"Profile"},
      responses = {
          @ApiResponse(description = "Success", responseCode = "200", content =
            @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                array = @ArraySchema(schema = @Schema(implementation = FindMyProfileOutputDTO.class))
            )
          ),
          @ApiResponse(description = "Unhautorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
          @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
      }
  )
  public ResponseEntity<FindMyProfileOutputDTO> findMyUser(
      Authentication authentication
  ){
    CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

    return new ResponseEntity<>(
        this.profileService.findMyUser(
            new FindMyProfileInputDTO(
                user.getId()
            )
        ),
        HttpStatus.OK
    );
  }

  @Operation(
      summary = "Atualiza informações do meu usuário",
      description = "Altera nome e/ou e-mail do meu usuário",
      tags = {"Profile"},
      responses = {
          @ApiResponse(description = "Success", responseCode = "200", content =
            @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                array = @ArraySchema(schema = @Schema(implementation = UpdateProfileOutputDTO.class))
            )
          ),
          @ApiResponse(description = "Unhautorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
          @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
      }
  )
  @PutMapping
  public ResponseEntity<UpdateProfileOutputDTO> update(
      Authentication authentication,

      @RequestBody
      @Valid
      UpdateProfileRequestDTO input
  ){
    CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

    return new ResponseEntity(
        this.profileService.update(
            new UpdateProfileInputDTO(
                user.getId(),
                input
            )
        ),
        HttpStatus.OK
    );
  }
}
