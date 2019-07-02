package com.app.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="bad input for product form")
public class NotValidProductException extends RuntimeException{

  private String message;
  private Map<String, Object> errors;

  public NotValidProductException(String message, Map<String, Object> errors) {
    this.message = message;
    this.errors = errors;
  }
  public String getMessage() {
    return message;
  }

  public Map<String, Object> getErrors() {
    return errors;
  }
}
