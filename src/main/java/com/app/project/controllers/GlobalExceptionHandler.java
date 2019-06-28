package com.app.project.controllers;

import com.app.project.exceptions.CustomerAlreadyExists;
import com.app.project.exceptions.MailException;
import com.app.project.exceptions.NotValidInputException;
import com.app.project.exceptions.NotValidProductException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({NotValidProductException.class})
  public String notValidProductException() {
    return "productError";
  }

  @ExceptionHandler(NotValidInputException.class)
  public String notValidInputExceptionHandler() {
    return "registrationError";
  }

  @ExceptionHandler(MailException.class)
  public String emailSendingErrorExceptionHandler() {
    return "emailSendingErrorExceptionHandler";
  }

  @ExceptionHandler(CustomerAlreadyExists.class)
  public String customerAlreadyExistsExceptionHandler() {
    return "customerError";
  }

}
