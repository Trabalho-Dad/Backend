package com.dad.sales_api.auth.dto.output;

public record RegisterOutputDTO(
    Integer id,
    String name,
    String cpf,
    String email
) {

}