package com.dad.sales_api.user.dto.input;

import com.dad.sales_api.user.dto.request.UpdateUserRequestDTO;

public record UpdateUserInputDTO (
    Integer id,
    String name,
    String email
){
  public UpdateUserInputDTO(Integer id, UpdateUserRequestDTO input) {
    this(
        id,
        input.name(),
        input.email()
    );
  }
}
