package com.dad.sales_api.shared.helpers.dto;

public record ValidatorOutputDTO(
    Boolean valid,
    String type,
    String formatted
){
}
