package com.app.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Customer with that email already exists in the database")
public class CustomerAlreadyExists extends RuntimeException {

  private String message;

  public CustomerAlreadyExists(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}

