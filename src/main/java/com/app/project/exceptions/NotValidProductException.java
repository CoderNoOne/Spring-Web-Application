package com.app.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="bad input for product form")
public class NotValidProductException extends RuntimeException{

  private String message;

  public NotValidProductException(String message) {
    this.message = message;
  }
  public String getMessage() {
    return message;
  }
}
