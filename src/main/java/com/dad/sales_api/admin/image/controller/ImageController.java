package com.dad.sales_api.admin.image.controller;

import com.dad.sales_api.admin.image.dto.input.CreateImageInputDTO;
import com.dad.sales_api.admin.image.dto.input.FindManyImagesInputDTO;
import com.dad.sales_api.admin.image.dto.output.CreateImageOutputDTO;
import com.dad.sales_api.admin.image.dto.output.FindManyImagesOutputDTO;
import com.dad.sales_api.admin.image.dto.query_params.FindManyImagesQueryParamsDTO;
import com.dad.sales_api.admin.image.dto.request.CreateImageRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dad.sales_api.admin.image.service.ImageService;

import lombok.RequiredArgsConstructor;

@RestController("adminImageController")
@RequiredArgsConstructor
@RequestMapping("/api/admin/images")
public class ImageController {
  private final ImageService service;

  @PostMapping
  public ResponseEntity<CreateImageOutputDTO> create(
      @RequestBody
      @Valid
      CreateImageRequestDTO input
  ){
    return new ResponseEntity<>(
        this.service.create(
            new CreateImageInputDTO(input)
        ),
        HttpStatus.CREATED
    );
  }

  @GetMapping
  public ResponseEntity<FindManyImagesOutputDTO> findMany(
      @ModelAttribute
      FindManyImagesQueryParamsDTO query
  ){
    return new ResponseEntity<>(
        this.service.findMany(
            new FindManyImagesInputDTO(query)
        ),
        HttpStatus.OK
    );
  }
}
