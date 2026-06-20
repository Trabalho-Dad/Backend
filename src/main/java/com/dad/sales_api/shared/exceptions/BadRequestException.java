package com.dad.sales_api.shared.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends SalesException{
  public BadRequestException(String message){
    super(message);
  }

  public BadRequestException(String message, Throwable error){
    super(message, error);
  }
  
  @Override
  public HttpStatus getStatus(){
    return HttpStatus.BAD_REQUEST;
  }
}
