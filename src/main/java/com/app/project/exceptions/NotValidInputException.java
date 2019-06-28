package com.app.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="bad input")
public class NotValidInputException extends RuntimeException {

  private String message;

  public NotValidInputException(String message) {
    this.message = message;
  }
  public String getMessage() {
    return message;
  }
}

