package com.app.project.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoxList {

  private List<Box> boxes = new ArrayList<>();

  public void add(Box box) {
    boxes.add(box);
    BigDecimal sum = sum();
    boxes.forEach(element -> element.setPercentage(element.getSum().divide(sum, new MathContext(4, RoundingMode.HALF_UP)).multiply(new BigDecimal("100"), new MathContext(4, RoundingMode.HALF_UP))));
  }

  private BigDecimal sum() {
    return boxes.stream()
            .map(Box::getSum)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

}
