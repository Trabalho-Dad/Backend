package com.dad.sales_api.admin.figure.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dad.sales_api.admin.figure.dto.input.CreateFigureInputDTO;
import com.dad.sales_api.admin.figure.dto.input.FindManyFiguresInputDTO;
import com.dad.sales_api.admin.figure.dto.input.IncreaseOrDecreaseQuantityInputDTO;
import com.dad.sales_api.admin.figure.dto.output.CreateFigureOutputDTO;
import com.dad.sales_api.admin.figure.dto.output.FindFigureByIdOutputDTO;
import com.dad.sales_api.admin.figure.dto.output.FindManyFiguresOutputDTO;
import com.dad.sales_api.admin.figure.dto.output.IncreaseOrDecreaseQuantityOutputDTO;
import com.dad.sales_api.admin.figure.dto.query_params.FindManyFiguresQueryParamsDTO;
import com.dad.sales_api.admin.figure.dto.request.CreateFigureRequestDTO;
import com.dad.sales_api.admin.figure.service.FigureService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@RestController("adminFigureController")
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/admin/figures")
public class FigureController {
  private final FigureService service;

  @GetMapping()
  public ResponseEntity<FindManyFiguresOutputDTO> findMany(
    @ModelAttribute
    @Valid
    FindManyFiguresQueryParamsDTO query
  ){
    return ResponseEntity.ok(
      this.service.findMany(
        new FindManyFiguresInputDTO(query)
      )
    );
  }

  @GetMapping("/{id}")
  public ResponseEntity<FindFigureByIdOutputDTO> findById(
    @PathVariable
    @Valid
    @Min(value = 1, message = "O id deve ser maior ou igual a 1")
    Integer id
  ){
    return ResponseEntity.ok(
      this.service.findById(id)
    );
  }

  @PostMapping()
  public ResponseEntity<CreateFigureOutputDTO> create(
    @RequestBody
    @Valid
    CreateFigureRequestDTO input
  ){
    return ResponseEntity.status(HttpStatus.CREATED).body(
      this.service.create(
        new CreateFigureInputDTO(input)
      )
    );
  }

  @PatchMapping("/{id}/quantity/increase/{quantity}")
  public ResponseEntity<IncreaseOrDecreaseQuantityOutputDTO> increaseQuantity(
    @PathVariable
    @Valid
    @Min(value = 1, message = "O id deve ser maior ou igual a 1")
    Integer id,

    @PathVariable
    @Valid
    @Min(value = 1, message = "A quantidade inserida deve ser maior ou igual a 1")
    Integer quantity
  ){
    return ResponseEntity.ok(
      this.service.increaseQuantity(
        new IncreaseOrDecreaseQuantityInputDTO(id, quantity)
      )
    );
  }

  @PatchMapping("/{id}/quantity/decrease/{quantity}")
  public ResponseEntity<IncreaseOrDecreaseQuantityOutputDTO> decreaseQuantity(
    @PathVariable
    @Valid
    @Min(value = 1, message = "O id deve ser maior ou igual a 1")
    Integer id,

    @PathVariable
    @Valid
    @Min(value = 1, message = "A quantidade a ser retirada deve ser maior ou igual a 1")
    Integer quantity
  ){
    return ResponseEntity.ok(
      this.service.decreaseQuantity(
        new IncreaseOrDecreaseQuantityInputDTO(id, quantity)
      )
    );
  }
}
