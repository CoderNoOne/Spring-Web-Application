package com.app.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class CustomerDto {

  private Integer id;
  @Size(min = 2, max = 25)
  private String firstName;

  @Size(min = 2, max = 25)
  private String lastName;

  @Size(min = 5, max = 30)
  @Pattern(regexp = ".+@gmail.com")
  private String emailAddress;

  @Size(min = 5)
  private String password;

}
