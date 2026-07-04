package com.dad.sales_api.admin.category.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dad.sales_api.admin.category.dto.input.CreateCategoryInputDTO;
import com.dad.sales_api.admin.category.dto.input.FindManyCategoriesInputDTO;
import com.dad.sales_api.admin.category.dto.input.UpdateCategoryInputDTO;
import com.dad.sales_api.admin.category.dto.output.CreateCategoryOutputDTO;
import com.dad.sales_api.admin.category.dto.output.FindCategoryByIdOutputDTO;
import com.dad.sales_api.admin.category.dto.output.FindManyCategoriesOutputDTO;
import com.dad.sales_api.admin.category.dto.output.UpdateCategoryOutputDTO;
import com.dad.sales_api.admin.category.dto.query_params.FindManyCategoriesQueryParamsDTO;
import com.dad.sales_api.admin.category.dto.request.CreateCategoryRequestDTO;
import com.dad.sales_api.admin.category.dto.request.UpdateCategoryRequestDTO;
import com.dad.sales_api.admin.category.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/admin/category")
@RequiredArgsConstructor
public class CategoryController {
  private final CategoryService categoryService;

  @GetMapping("")
  public ResponseEntity<FindManyCategoriesOutputDTO> findMany(
    @ModelAttribute
    @Valid
    FindManyCategoriesQueryParamsDTO query
  ) {
    return ResponseEntity.ok(
      this.categoryService.findMany(
        new FindManyCategoriesInputDTO(query)
      )
    );
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<FindCategoryByIdOutputDTO> getMethodName(
    @PathVariable
    @Valid
    @Min(1)
    Integer id
  ) {
    return ResponseEntity.ok(
      this.categoryService.findById(id)
    );
  }
  
  @PostMapping("")
  public ResponseEntity<CreateCategoryOutputDTO> create(
    @RequestBody
    @Valid
    CreateCategoryRequestDTO input
  ) {
    return ResponseEntity.ok(
      this.categoryService.create(
        new CreateCategoryInputDTO(input)
      )
    );
  }

  @PutMapping("/{id}")
  public ResponseEntity<UpdateCategoryOutputDTO> update(
    @PathVariable 
    Integer id, 
    @RequestBody
    @Valid
    UpdateCategoryRequestDTO input
  ) {
    return ResponseEntity.ok(
      this.categoryService.update(
        new UpdateCategoryInputDTO(id, input)
      )    
    );
  }

  @PatchMapping("/{id}")
  public ResponseEntity<UpdateCategoryOutputDTO> updateStatus(
    @PathVariable 
    Integer id
  ) {
    return ResponseEntity.ok(
      this.categoryService.updateStatus(id)    
    );
  }
}
