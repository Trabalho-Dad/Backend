package com.dad.sales_api.address.dto.input;

import com.dad.sales_api.address.dto.request.CreateAddressRequestDTO;
import com.dad.sales_api.shared.helpers.NormalizeInput;

public record CreateAddressInputDTO(

    String cep,

    String state,

    String city,

    String neighborhood,

    String street,

    String number,

    String complement,

    Integer userId

) {
  public CreateAddressInputDTO(CreateAddressRequestDTO input, Integer userId) {
    this(
        NormalizeInput.cep(input.cep()),
        NormalizeInput.addressInfos(input.state()),
        NormalizeInput.addressInfos(input.city()),
        NormalizeInput.addressInfos(input.neighborhood()),
        NormalizeInput.addressInfos(input.street()),
        NormalizeInput.removeBlank(input.number()),
        NormalizeInput.removeBlank(input.complement()),
        userId
    );
  }
}