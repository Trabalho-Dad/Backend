package com.dad.sales_api.shared.config.handlers.dto.output;

public record ExceptionOutputDTO (
    String message,
    String timestamp,
    Integer status,
    String error
){

}
