package com.app.project.dto;

import com.app.project.model.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

  private Integer id;
  @Size(min = 2, max = 25)
  @NotEmpty
  private String name;

  @Enumerated(EnumType.STRING)
  private Category category;

  @Range(min = 1)
  private BigDecimal price;
}
