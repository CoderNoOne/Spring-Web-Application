package com.app.project;
import com.app.project.model.Category;
import com.app.project.repository.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;
import java.util.stream.Collectors;


@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext run = SpringApplication.run(DemoApplication.class, args);

    ProductRepository productRepository = run.getBean("productRepository", ProductRepository.class);
//    System.out.println(productRepository.findAllProductsByCustomerEmail("michal@gmail.com"));
//    System.out.println(productRepository.getCustomerByCustomerEmail("michal@gmail.com"));

    System.out.println(productRepository.getCustomerByCustomerId(1));
  }
}
