package com.dad.sales_api.shared.exceptions;

import org.springframework.http.HttpStatus;

public abstract class SalesException extends RuntimeException{
  public SalesException(String message){
    super(message);
  }

  public SalesException(String message, Throwable error){
    super(message, error);
  }

  public abstract HttpStatus getStatus();
}
