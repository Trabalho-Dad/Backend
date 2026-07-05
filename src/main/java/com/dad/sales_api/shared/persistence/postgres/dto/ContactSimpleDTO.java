package com.dad.sales_api.shared.persistence.postgres.dto;

public record ContactSimpleDTO (
    Integer id,
    String value,
    String type
){
  public ContactSimpleDTO(Integer id, String value, String type) {
    this.id = id;
    this.value = value;
    this.type = type;
  }
}
