package com.dad.sales_api.address.dto.output;

public record CreateAddressOutputDTO(
    Integer id,

    String cep,

    String state,

    String city,

    String neighborhood,

    String street,

    String number,

    String complement
) {
}
