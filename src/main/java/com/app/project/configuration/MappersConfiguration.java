package com.app.project.configuration;

import com.app.project.mapper.CustomerMapper;
import com.app.project.mapper.ProductMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappersConfiguration {

  @Bean

  public CustomerMapper createConsumerMapper() {
    return Mappers.getMapper(CustomerMapper.class);
  }
  @Bean
  public ProductMapper createProductMapper() {
    return Mappers.getMapper(ProductMapper.class);
  }
}
