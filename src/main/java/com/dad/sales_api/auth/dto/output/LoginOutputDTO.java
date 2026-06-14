package com.dad.sales_api.auth.dto.output;

public record LoginOutputDTO (
  String token,
  int status,
  String message
) {
  
}
