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
  @Size(min = 2, max = 25, message = "Imię musi mieć długość {min}-{max} znaków. Wprowadzone: ${validatedValue} zawiera znaków: ${validatedValue.length()}")
  private String firstName;

  @Size(min = 2, max = 25, message = "Nazwisko musi mieć długość  {min}-{max} znaków. Wprowadzone: ${validatedValue} zawiera znaków: ${validatedValue.length()}")
  private String lastName;

  @Size(min = 5, max = 30, message = "Adres mail musi mieć długość {min}-{max}")
  @Pattern(regexp = ".+@gmail.com", message = "Adres mail musi być w domenie gmail")
  private String emailAddress;

  @Size(min = 5, message = "Minimalna długość hasła to {min} znaków. Wprowadzone hasło ma tylko ${validatedValue.length()} znaków długości")
  private String password;

}
