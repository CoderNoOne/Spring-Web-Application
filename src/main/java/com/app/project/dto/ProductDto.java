package com.app.project.dto;

import com.app.project.model.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

  private Integer id;
  @Size(min = 2, max = 30, message = "Nazwa produktu musi składać się z {min}-{max} znaków. " +
          "Wprowadzona nazwa ${validatedValue} zawiera znaków ${validatedValue.length()}")
  private String name;

  @Enumerated(EnumType.STRING)
  private Category category;

  @Range(min = 1, message = "Cena produktu musi być dodatnia. Wprowadzona cena wynosi : ${validatedValue}")
  @NotNull(message = "Musisz podać cenę produktu")
  private BigDecimal price;
}
