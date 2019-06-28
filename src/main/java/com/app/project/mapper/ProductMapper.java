package com.app.project.mapper;

import com.app.project.dto.ProductDto;
import com.app.project.model.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {

  ProductDto mapProductToProductDto(Product product);

  Product mapProductDtoToProduct(ProductDto productDto);
}
