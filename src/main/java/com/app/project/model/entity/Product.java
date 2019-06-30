package com.app.project.model.entity;

import com.app.project.model.enums.Category;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Product implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  @Enumerated(EnumType.STRING)
  private Category category;

  private BigDecimal price;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "customer_id")
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Customer customer;
}
