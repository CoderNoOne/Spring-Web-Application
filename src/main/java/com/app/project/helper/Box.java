package com.app.project.helper;

import com.app.project.model.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Box {

  private Category category;
  private BigDecimal sum;
  private BigDecimal percentage;
}
