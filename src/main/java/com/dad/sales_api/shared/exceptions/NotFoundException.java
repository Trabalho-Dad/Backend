package com.dad.sales_api.shared.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends SalesException{
  public NotFoundException(String message){
    super(message);
  }

  public NotFoundException(String message, Throwable error){
    super(message, error);
  }
  
  @Override
  public HttpStatus getStatus(){
    return HttpStatus.NOT_FOUND;
  }
}
