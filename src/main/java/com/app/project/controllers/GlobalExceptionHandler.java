package com.app.project.controllers;

import com.app.project.exceptions.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(NotValidProductException.class)
  public ModelAndView handleNotValidProductException(NotValidProductException e) {
    return new ModelAndView("product_error", e.getErrors());
  }

  @ExceptionHandler(NotValidInputException.class)
  public ModelAndView handleNotValidInputException(NotValidInputException e) {
    return new ModelAndView("registration_error", e.getErrors());
  }

  @ExceptionHandler(MailException.class)
  public String handleMailException() {
    return "email_sending_error";
  }

  @ExceptionHandler(CustomerAlreadyExists.class)
  public String handleCustomerAlreadyExistsException() {
    return "customer_error";
  }

  @ExceptionHandler(UserNotFoundException.class)
  public String handleUserNotFoundException() {
    return "user_not_found";
  }
}
