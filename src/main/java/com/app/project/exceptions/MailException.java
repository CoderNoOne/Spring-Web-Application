package com.app.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Something went wrong with sending email")
public class MailException extends RuntimeException {

  private String message;

  public MailException(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
