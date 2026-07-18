package com.dad.sales_api.figure.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
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

@Tag(name = "Figure", description = "Rotas públicas para visualização dos bonecos")
@RestController("publicFigureController")
@RequestMapping("/api/figures")
@RequiredArgsConstructor
public class FigureController {
  private final FigureService figureService;

  @Operation(
      summary = "Retorna os bonecos",
      description = "Retorna todos os bonecos de acordo com uma filtragem",
      tags = { "Figure" },
      responses = {
          @ApiResponse(description = "Success", responseCode = "200", content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              array = @ArraySchema(schema = @Schema(implementation = FindManyFiguresOutputDTO.class))
          )
          ),
          @ApiResponse(description = "Unhautorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
          @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
      }
  )
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

  @Operation(
      summary = "Retorna os detalhes de um boneco",
      description = "Retorna informações de um boneco com base no id do mesmo",
      tags = { "Figure" },
      responses = {
          @ApiResponse(description = "Success", responseCode = "200", content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              array = @ArraySchema(schema = @Schema(implementation = FindFigureByIdOutputDTO.class))
          )
          ),
          @ApiResponse(description = "Unhautorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
          @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
      }
  )
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
