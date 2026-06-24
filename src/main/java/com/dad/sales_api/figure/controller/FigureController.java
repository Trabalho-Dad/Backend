package com.dad.sales_api.figure.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dad.sales_api.figure.dto.input.FindManyFiguresInputDTO;
import com.dad.sales_api.figure.dto.output.FindFigureByIdOutputDTO;
import com.dad.sales_api.figure.dto.output.FindManyFiguresOutputDTO;
import com.dad.sales_api.figure.dto.query_params.FindManyFiguresQueryParamsDTO;
import com.dad.sales_api.figure.service.FigureService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@RestController("publicFigureController")
@RequestMapping("/figure")
@RequiredArgsConstructor
public class FigureController {
  private final FigureService figureService;

  @GetMapping("")
  public ResponseEntity<FindManyFiguresOutputDTO> findMany(
    @Valid
    @ModelAttribute
    FindManyFiguresQueryParamsDTO query
  ) {
    return ResponseEntity.ok(
      figureService.findMany(new FindManyFiguresInputDTO(query))
    );
  }

  @GetMapping("/{id}")
  public ResponseEntity<FindFigureByIdOutputDTO> findById(
    @PathVariable
    Integer id
  ) {   
    return ResponseEntity.ok(   
      figureService.findById(id)
    );
  }
}
