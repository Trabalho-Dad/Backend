package com.dad.sales_api.order.controller;

import com.dad.sales_api.order.dto.input.*;
import com.dad.sales_api.order.dto.output.*;
import com.dad.sales_api.order.dto.query_params.FindManyOrdersQueryParamsDTO;
import com.dad.sales_api.order.dto.request.AddCouponRequestDTO;
import com.dad.sales_api.order.dto.request.AddItemsRequestDTO;
import com.dad.sales_api.order.dto.request.FinishOrderRequestDTO;
import com.dad.sales_api.order.dto.request.RemoveItemRequestDTO;
import com.dad.sales_api.order.service.OrderService;
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
      summary = "Retorna um pedido",
      description = "Retorna os detalhes de um pedido pelo seu id",
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
  @GetMapping("/{id}")
  public ResponseEntity<FindOrderByIdOutputDTO> findById(
      Authentication authentication,

      @PathVariable
      @Valid
      @NotNull(message = "{validation.order-id.required}")
      @Min(value = 1, message = "{validation.order-id.min-value}")
      Integer id
  ){
    CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

    return new ResponseEntity<>(
        this.orderService.findById(
            new FindOrderByIdInputDTO(
                id,
                user.getId()
            )
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
  @PostMapping("/add-items")
  public ResponseEntity<AddItemsOutputDTO> addItems(
      Authentication authentication,

      @RequestBody
      @Valid
      AddItemsRequestDTO input
  ) {
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

  @Operation(
      summary = "Remove um item do meu pedido",
      description = "Remove determinada quantidade de um boneco do meu pedido",
      tags = { "Order" },
      responses = {
          @ApiResponse(description = "Success", responseCode = "200", content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              array = @ArraySchema(schema = @Schema(implementation = RemoveItemOutputDTO.class))
          )
          ),
          @ApiResponse(description = "Unhautorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
          @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
      }
  )
  @PostMapping("/remove-items")
  public ResponseEntity<RemoveItemOutputDTO> removeItem(
      Authentication authentication,

      @RequestBody
      @Valid
      RemoveItemRequestDTO input
  ) {
    CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

    return new ResponseEntity(
        this.orderService.removeItem(
            new RemoveItemInputDTO(
                input,
                user.getId()
            )
        ),
        HttpStatus.OK
    );
  }

  @Operation(
      summary = "Adiciona um cupom ao meu pedido",
      description = "Adiciona um cupom de desconto ao meu pedido",
      tags = { "Order" },
      responses = {
          @ApiResponse(description = "Success", responseCode = "200", content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              array = @ArraySchema(schema = @Schema(implementation = AddCouponOutputDTO.class))
          )
          ),
          @ApiResponse(description = "Unhautorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
          @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
      }
  )
  @PostMapping("/add-cupons")
  public ResponseEntity<AddCouponOutputDTO> addCoupon(
      Authentication authentication,

      @RequestBody
      @Valid
      AddCouponRequestDTO input
  ){
    CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

    return new ResponseEntity<>(
        this.orderService.addCoupon(
            new AddCouponInputDTO(
                user.getId(),
                input.orderId(),
                input.code()
            )
        ),
        HttpStatus.OK
    );

  }

  @Operation(
      summary = "Finaliza um pedido",
      description = "Finaliza meu pedido em aberto",
      tags = { "Order" },
      responses = {
          @ApiResponse(description = "Success", responseCode = "200", content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              array = @ArraySchema(schema = @Schema(implementation = FinishOrderOutputDTO.class))
          )
          ),
          @ApiResponse(description = "Unhautorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
          @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
      }
  )
  @PostMapping("/finish")
  public ResponseEntity<FinishOrderOutputDTO> finishOrder(
      Authentication authentication,

      @RequestBody
      @Valid
      FinishOrderRequestDTO input
  ){
    CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

    return new ResponseEntity<>(
        this.orderService.finishOrder(
            new FinishOrderInputDTO(
                user.getId(),
                input
            )
        ),
        HttpStatus.OK
    );
  }

  @Operation(
      summary = "Cancela um pedido",
      description = "Cancela um pedido que não esteja entregue",
      tags = { "Order" },
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
  @PatchMapping("/cancel/{orderId}")
  public ResponseEntity<Void> cancelOrder(
      Authentication authentication,

      @PathVariable
      @Valid
      Integer orderId
  ){
    CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

    this.orderService.cancelOrder(
        new CancelOrderInputDTO(
            orderId,
            user.getId()
        )
    );

    return ResponseEntity.noContent().build();
  }
}
