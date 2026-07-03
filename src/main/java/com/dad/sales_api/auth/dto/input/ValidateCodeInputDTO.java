package com.dad.sales_api.auth.dto.input;

public record ValidateCodeInputDTO(
    String email,
    Integer code
) {
}
