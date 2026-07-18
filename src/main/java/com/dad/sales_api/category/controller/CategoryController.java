package com.dad.sales_api.category.controller;

import com.dad.sales_api.admin.category.dto.output.FindManyCategoriesOutputDTO;
import com.dad.sales_api.category.dto.output.FindAllCategoriesOutputDTO;
import com.dad.sales_api.category.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Tag(name = "Category", description = "Rotas públicas para visualização das categorias")
@RestController("publicCategoryController")
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
  private final CategoryService categoryService;

  @Operation(
      summary = "Retorna as categorias",
      description = "Retorna todas as categorias ativas neste momento",
      tags = { "Figure" },
      responses = {
          @ApiResponse(description = "Success", responseCode = "200", content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              array = @ArraySchema(schema = @Schema(implementation = FindManyCategoriesOutputDTO.class))
          )
          ),
          @ApiResponse(description = "Unhautorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
          @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
      }
  )
  @GetMapping("/find-all")
  public ResponseEntity<List<FindAllCategoriesOutputDTO>> findAll(){
    return new ResponseEntity<>(
        this.categoryService.findAll(),
        HttpStatus.OK
    );
  }
}
