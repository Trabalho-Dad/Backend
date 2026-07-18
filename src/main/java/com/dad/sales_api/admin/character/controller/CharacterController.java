package com.dad.sales_api.admin.character.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dad.sales_api.admin.character.dto.input.CreateCharacterInputDTO;
import com.dad.sales_api.admin.character.dto.input.FindManyCharactersInputDTO;
import com.dad.sales_api.admin.character.dto.input.UpdateCharacterInputDTO;
import com.dad.sales_api.admin.character.dto.output.CreateCharacterOutputDTO;
import com.dad.sales_api.admin.character.dto.output.FindCharacterByIdOutputDTO;
import com.dad.sales_api.admin.character.dto.output.FindManyCharacterOutputDTO;
import com.dad.sales_api.admin.character.dto.output.UpdateCharacterOutputDTO;
import com.dad.sales_api.admin.character.dto.query_params.FindManyCharactersQueryParamsDTO;
import com.dad.sales_api.admin.character.dto.request.CreateCharacterRequestDTO;
import com.dad.sales_api.admin.character.dto.request.UpdateCharacterRequestDTO;
import com.dad.sales_api.admin.character.service.CharacterService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/admin/characters")
@RequiredArgsConstructor
public class CharacterController {
  private final CharacterService characterService;

  @GetMapping("")
  public ResponseEntity<FindManyCharacterOutputDTO> findMany(
      @ModelAttribute
      FindManyCharactersQueryParamsDTO query
  ) {
    return new ResponseEntity(
        this.characterService.findMany(
            new FindManyCharactersInputDTO(query)
        ),
        HttpStatus.OK
    );
  }

  @GetMapping("/{id}")
  public ResponseEntity<FindCharacterByIdOutputDTO> findById(
      @PathVariable
      @Valid
      @Min(value = 1, message = "O id não pode ser menor ou igual a 0")
      Integer id
  ) {
    return new ResponseEntity(
        characterService.findById(id),
        HttpStatus.OK
    );
  }

  @PutMapping("/{id}")
  public ResponseEntity<UpdateCharacterOutputDTO> update(
      @PathVariable
      @Valid
      @Min(value = 1, message = "O id não pode ser menor ou igual a 0")
      Integer id,

      @RequestBody
      @Valid
      UpdateCharacterRequestDTO input
  ) {
    return new ResponseEntity(
        characterService.update(
            new UpdateCharacterInputDTO(id, input)
        ),
        HttpStatus.OK
    );
  }

  @PostMapping("")
  public ResponseEntity<CreateCharacterOutputDTO> createCharacter(
      @RequestBody
      @Valid
      CreateCharacterRequestDTO input
  ) {
    return new ResponseEntity(
        this.characterService.create(
            new CreateCharacterInputDTO(
                input
            )
        ),
        HttpStatus.CREATED
    );
  }

  @PatchMapping("/{id}")
  public ResponseEntity<UpdateCharacterOutputDTO> update(
      @PathVariable
      @Valid
      @Min(1)
      Integer id
  ) {
    return new ResponseEntity(
        this.characterService.updateStatus(id),
        HttpStatus.OK
    );
  }
}