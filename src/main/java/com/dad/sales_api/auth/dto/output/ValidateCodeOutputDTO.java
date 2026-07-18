package com.dad.sales_api.auth.dto.output;

public record ValidateCodeOutputDTO(
    String code,
    Boolean valid
) {
}
