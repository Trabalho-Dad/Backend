package com.dad.sales_api.address.controller;

import com.dad.sales_api.address.dto.input.CreateAddressInputDTO;
import com.dad.sales_api.address.dto.input.DeleteAddressInputDTO;
import com.dad.sales_api.address.dto.input.FindMyAddressesInputDTO;
import com.dad.sales_api.address.dto.input.UpdateAddressInputDTO;
import com.dad.sales_api.address.dto.output.CreateAddressOutputDTO;
import com.dad.sales_api.address.dto.output.FindMyAddressesOutputDTO;
import com.dad.sales_api.address.dto.output.UpdateAddressOutputDTO;
import com.dad.sales_api.address.dto.request.CreateAddressRequestDTO;
import com.dad.sales_api.address.dto.request.UpdateAddressRequestDTO;
import com.dad.sales_api.address.service.AddressService;
import com.dad.sales_api.shared.config.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Address", description = "Informações sobre os endereços do meu usuário")
@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {
  private final AddressService addressService;

  @Operation(
      summary = "Retorna os meus endereços",
      description = "Retorna todos os meus endereços cadastrados",
      tags = { "Address" },
      responses = {
          @ApiResponse(description = "Success", responseCode = "200", content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              array = @ArraySchema(schema = @Schema(implementation = FindMyAddressesOutputDTO.class))
          )
          ),
          @ApiResponse(description = "Unhautorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
          @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
      }
  )
  @GetMapping
  public ResponseEntity<FindMyAddressesOutputDTO> findMyAddresses(
      Authentication authentication
  ){
    CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

    return new ResponseEntity<>(
        this.addressService.findMyAddresses(
            new FindMyAddressesInputDTO(
                user.getId()
            )
        ),
        HttpStatus.OK
    );
  }

  @Operation(
      summary = "Adiciona um endereço",
      description = "Adiciona um endereço para o meu usuário",
      tags = { "Address" },
      responses = {
          @ApiResponse(description = "Created", responseCode = "201", content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              array = @ArraySchema(schema = @Schema(implementation = CreateAddressOutputDTO.class))
          )
          ),
          @ApiResponse(description = "Unhautorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
          @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
      }
  )
  @PostMapping
  public ResponseEntity<CreateAddressOutputDTO> create(
      Authentication authentication,

      @RequestBody
      @Valid
      CreateAddressRequestDTO input
  ){
      CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

    return new ResponseEntity<>(
        this.addressService.createAddress(
            new CreateAddressInputDTO(
                input,
                user.getId()
            )
        ),
        HttpStatus.CREATED
    );
  }

  @Operation(
      summary = "Altera um endereço",
      description = "Altera um endereço para o meu usuário",
      tags = { "Address" },
      responses = {
          @ApiResponse(description = "Success", responseCode = "200", content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              array = @ArraySchema(schema = @Schema(implementation = UpdateAddressOutputDTO.class))
          )
          ),
          @ApiResponse(description = "Unhautorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
          @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
      }
  )
  @PutMapping("/{id}")
  public ResponseEntity<UpdateAddressOutputDTO> update(
      Authentication authentication,

      @PathVariable
      @Valid
      @NotNull(message = "{validation.address-id.required}")
      @Min(value = 1, message = "{validation.address-id.min-value}")
      Integer id,

      @RequestBody
      @Valid
      UpdateAddressRequestDTO input
  ){
    CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

    return new ResponseEntity<>(
        this.addressService.update(
            new UpdateAddressInputDTO(input, id, user.getId())
        ),
        HttpStatus.OK
    );
  }

  @Operation(
      summary = "Deleta um endereço",
      description = "Deleta um endereço do meu usuário",
      tags = { "Address" },
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
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(
      Authentication authentication,

      @PathVariable
      @Valid
      @NotNull(message = "{validation.address-id.required}")
      @Min(value = 1, message = "{validation.address-id.min-value}")
      Integer id
  ){
    CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

    this.addressService.delete(
        new DeleteAddressInputDTO(
            id,
            user.getId()
        )
    );

    return ResponseEntity.noContent().build();
  }
}
