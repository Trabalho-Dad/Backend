package com.dad.sales_api.admin.coupon.controller;

import com.dad.sales_api.admin.coupon.dto.input.CreateCouponInputDTO;
import com.dad.sales_api.admin.coupon.dto.input.FindCouponByIdInputDTO;
import com.dad.sales_api.admin.coupon.dto.input.FindManyCouponsInputDTO;
import com.dad.sales_api.admin.coupon.dto.output.CreateCouponOutputDTO;
import com.dad.sales_api.admin.coupon.dto.output.FindCouponByIdOutputDTO;
import com.dad.sales_api.admin.coupon.dto.output.FindManyCouponsOutputDTO;
import com.dad.sales_api.admin.coupon.dto.query_params.FindManyCouponsQueryParamsDTO;
import com.dad.sales_api.admin.coupon.dto.request.CreateCouponRequestDTO;
import com.dad.sales_api.admin.coupon.service.CouponService;
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
import org.springframework.web.bind.annotation.*;

@Tag(name = "Admin Coupons", description = "Rotas para CRUD de cupons de desconto")
@RestController("adminCouponController")
@RequiredArgsConstructor
@RequestMapping("/api/admin/coupons")
public class CouponController {
  private final CouponService couponService;

  @Operation(
      summary = "Busca todos os cupons",
      description = "Retorna todos os cupons de desconto cadastrados",
      tags = { "Admin Coupons" },
      responses = {
          @ApiResponse(description = "Success", responseCode = "200", content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              array = @ArraySchema(schema = @Schema(implementation = FindManyCouponsOutputDTO.class))
          )
          ),
          @ApiResponse(description = "Unhautorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
          @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
      }
  )
  @GetMapping
  public ResponseEntity<FindManyCouponsOutputDTO> findMany(
      @ModelAttribute
      @Valid
      FindManyCouponsQueryParamsDTO query
  ){
    return new ResponseEntity<>(
        this.couponService.findMany(
            new FindManyCouponsInputDTO(
                query.code(),
                query.active(),
                query.page(),
                query.take()
            )
        ),
        HttpStatus.OK
    );
  }

  @Operation(
      summary = "Busca um cupom",
      description = "Retorna um cumpom de desconto com base no id informado",
      tags = { "Admin Coupons" },
      responses = {
          @ApiResponse(description = "Success", responseCode = "200", content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              array = @ArraySchema(schema = @Schema(implementation = FindCouponByIdOutputDTO.class))
          )
          ),
          @ApiResponse(description = "Unhautorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
          @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
      }
  )
  @GetMapping("/{id}")
  public ResponseEntity<FindCouponByIdOutputDTO> findById(
      @PathVariable
      @Valid
      @NotNull(message = "validation.coupon-id.required")
      @Min(value = 1, message = "validation.coupon-id.min-value")
      Integer id
  ){
    return new ResponseEntity<>(
        this.couponService.findById(
            new FindCouponByIdInputDTO(id)
        ),
        HttpStatus.OK
    );
  }

  @Operation(
      summary = "Cadastra um cupom",
      description = "Realiza cadastro de um cumpom de desconto",
      tags = { "Admin Coupons" },
      responses = {
          @ApiResponse(description = "Created", responseCode = "201", content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              array = @ArraySchema(schema = @Schema(implementation = CreateCouponOutputDTO.class))
          )
          ),
          @ApiResponse(description = "Unhautorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
          @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
      }
  )
  @PostMapping
  public ResponseEntity<CreateCouponOutputDTO> create(
      @RequestBody
      @Valid
      CreateCouponRequestDTO input
  ){
    return new ResponseEntity<>(
        this.couponService.createCoupon(
            new CreateCouponInputDTO(
                input
            )
        ),
        HttpStatus.CREATED
    );
  }
}
