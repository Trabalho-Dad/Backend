package com.dad.sales_api.auth.dto.output;

public record ValidateCodeOutputDTO(
    Integer code,
    Boolean valid
) {
}
