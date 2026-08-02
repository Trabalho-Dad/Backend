package com.dad.sales_api.profile.dto.input;

import com.dad.sales_api.profile.dto.request.UpdateProfileRequestDTO;

public record UpdateProfileInputDTO(
    Integer id,
    String name,
    String email
){
  public UpdateProfileInputDTO(Integer id, UpdateProfileRequestDTO input) {
    this(
        id,
        input.name(),
        input.email()
    );
  }
}
