package com.app.project.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User couldn't have been found in a DB")
public class UserNotFoundException extends RuntimeException {

  private String message;
}
