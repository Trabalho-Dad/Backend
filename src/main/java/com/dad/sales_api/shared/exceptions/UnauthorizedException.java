package com.dad.sales_api.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends SalesException{
  public UnauthorizedException(String message) {
    super(message);
  }

  public UnauthorizedException(String message, Throwable error) {
    super(message, error);
  }

  @Override
  public HttpStatus getStatus() {
    return HttpStatus.UNAUTHORIZED;
  }
}
