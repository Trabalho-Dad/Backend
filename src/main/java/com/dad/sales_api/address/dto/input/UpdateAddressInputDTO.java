package com.dad.sales_api.address.dto.input;

import com.dad.sales_api.address.dto.request.CreateAddressRequestDTO;
import com.dad.sales_api.address.dto.request.UpdateAddressRequestDTO;
import com.dad.sales_api.shared.helpers.NormalizeInput;

public record UpdateAddressInputDTO (
    Integer id,

    String cep,

    String state,

    String city,

    String neighborhood,

    String street,

    String number,

    String complement,

    Integer userId
){
  public UpdateAddressInputDTO(UpdateAddressRequestDTO input, Integer id,Integer userId) {
    this(
        id,
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
