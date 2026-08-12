package com.dad.sales_api.shared.persistence.postgres.dto;

public record AddressSimpleDTO(
    Integer id,
    String cep,
    String state,
    String city,
    String neighborhood,
    String street,
    String number,
    String complement
) {
  public AddressSimpleDTO(Integer id, String cep, String state, String city, String neighborhood, String street, String number, String complement) {
    this.id = id;
    this.cep = cep;
    this.state = state;
    this.city = city;
    this.neighborhood = neighborhood;
    this.street = street;
    this.number = number;
    this.complement = complement;
  }
}
