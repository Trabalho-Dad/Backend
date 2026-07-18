package com.dad.sales_api.auth.dto.output;

public record LoginOutputDTO (
  String token,
  String message
) {
  
}
