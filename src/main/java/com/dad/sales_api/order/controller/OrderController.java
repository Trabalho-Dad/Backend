package com.dad.sales_api.order.controller;

import com.dad.sales_api.order.dto.input.AddItemsInputDTO;
import com.dad.sales_api.order.dto.input.FindManyOrdersInputDTO;
import com.dad.sales_api.order.dto.output.AddItemsOutputDTO;
import com.dad.sales_api.order.dto.output.FindManyOrdersOutputDTO;
import com.dad.sales_api.order.dto.query_params.FindManyOrdersQueryParamsDTO;
import com.dad.sales_api.order.dto.request.AddItemsRequestDTO;
import com.dad.sales_api.order.service.OrderService;
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

@Tag(name = "Order", description = "Rotas para CRUD de carrinhos/pedidos")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
  private final OrderService orderService;

  @Operation(
      summary = "Retorna meus pedidos",
      description = "Retorna todos os pedidos que já realizei",
      tags = { "Order" },
      responses = {
          @ApiResponse(description = "Success", responseCode = "200", content =
            @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                array = @ArraySchema(schema = @Schema(implementation = FindManyOrdersOutputDTO.class))
            )
          ),
          @ApiResponse(description = "Unhautorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
          @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
      }
  )
  @GetMapping()
  public ResponseEntity<FindManyOrdersOutputDTO> findMany(
      Authentication authentication,

      @ModelAttribute
      @Valid
      FindManyOrdersQueryParamsDTO query
  ){
    CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

    return new ResponseEntity<>(
        this.orderService.findMyOrders(
            new FindManyOrdersInputDTO(query, user.getId())
        ),
        HttpStatus.OK
    );
  }

  @Operation(
      summary = "Adiciona um item ao meu pedido",
      description = "Adiciona uma determinada quantidade de um boneco ao meu pedido, além de criar um pedido se não existir",
      tags = { "Order" },
      responses = {
          @ApiResponse(description = "Success", responseCode = "200", content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              array = @ArraySchema(schema = @Schema(implementation = AddItemsOutputDTO.class))
          )
          ),
          @ApiResponse(description = "Unhautorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
          @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
      }
  )
  @PostMapping("/items")
  public ResponseEntity<AddItemsOutputDTO> addItems(
      Authentication authentication,

      @RequestBody
      @Valid
      AddItemsRequestDTO input
  ){
    CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

    return new ResponseEntity(
        this.orderService.addItem(
            new AddItemsInputDTO(
                input,
                user.getId()
            )
        ),
        HttpStatus.OK
    );
  }
}
