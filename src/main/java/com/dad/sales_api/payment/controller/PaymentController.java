package com.dad.sales_api.payment.controller;

import com.dad.sales_api.payment.dto.input.FindManyPaymentsInputDTO;
import com.dad.sales_api.payment.dto.input.PayInputDTO;
import com.dad.sales_api.payment.dto.output.FindManyPaymentsOutputDTO;
import com.dad.sales_api.payment.dto.output.PayOutputDTO;
import com.dad.sales_api.payment.dto.query_params.FindManyPaymentsQueryParamsDTO;
import com.dad.sales_api.payment.service.PaymentService;
import com.dad.sales_api.shared.config.CustomUserDetails;
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

@Tag(name = "Payment", description = "Rotas para pagamentos dentro da aplicação")
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
  private final PaymentService paymentService;

  @Operation(
      summary = "Retorna os pagamentos",
      description = "Retorna todas as parcelas e pagamentos que devo fazer",
      tags = { "Payment" },
      responses = {
          @ApiResponse(
              description = "Success",
              responseCode = "200",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = FindManyPaymentsOutputDTO.class)
              )
          ),
          @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
          @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
      }
  )
  @GetMapping
  public ResponseEntity<FindManyPaymentsOutputDTO> findMany(
      Authentication authentication,

      @ModelAttribute
      @Valid
      FindManyPaymentsQueryParamsDTO query
  ) {
    CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

    return ResponseEntity.ok(
        paymentService.findMany(
            new FindManyPaymentsInputDTO(
                query,
                user.getId()
            )
        )
    );
  }

  @Operation(
      summary = "Paga uma parcela",
      description = "Paga uma parcela pendente",
      tags = { "Payment" },
      responses = {
          @ApiResponse(description = "Success", responseCode = "200", content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              array = @ArraySchema(schema = @Schema(implementation = PayOutputDTO.class))
          )
          ),
          @ApiResponse(description = "Unhautorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
          @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
      }
  )
  @PatchMapping("/pay/{id}")
  public ResponseEntity<PayOutputDTO> pay(
      Authentication authentication,

      @PathVariable
      @Valid
      Integer id
  ){
    CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

    return new ResponseEntity<>(
        this.paymentService.pay(
            new PayInputDTO(
                user.getId(),
                id
            )
        ),
        HttpStatus.OK
    );
  }
}
