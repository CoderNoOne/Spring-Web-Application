package com.app.project.utils;

import com.app.project.dto.CustomerDto;
import com.app.project.dto.ProductDto;
import com.app.project.mapper.CustomerMapper;
import com.app.project.mapper.ProductMapper;
import com.app.project.model.Customer;
import com.app.project.model.Product;
import com.app.project.service.CustomerService;
import com.app.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public final class GlobalControllerUtil {

  private CustomerMapper customerMapper;
  private ProductMapper productMapper;
  private ProductService productService;
  private CustomerService customerService;
  private PasswordEncoder passwordEncoder;

  @Autowired
  public GlobalControllerUtil(CustomerMapper customerMapper, ProductMapper productMapper, ProductService productService, CustomerService customerService, PasswordEncoder passwordEncoder) {
    this.customerService = customerService;
    this.productService = productService;
    this.customerMapper = customerMapper;
    this.productMapper = productMapper;
    this.passwordEncoder = passwordEncoder;
  }

  public void setModelAttributes(Model model, CustomerDto customerDto, String email) {

    model.addAttribute("userLogin", customerDto);
    model.addAttribute("productList", productService.getCustomerProductsList(email));
    model.addAttribute("sumPriceByCategory", productService.allProductsSumPriceByCategory());
    model.addAttribute("sumPriceByCategoryByClient", productService.productsSumPriceByCategoryByCustomerEmail(customerDto.getEmailAddress()));
    model.addAttribute("sumPriceByAllCategoryByAllClients", productService.sumPriceByAllCategoryByAllClients());
  }

  public Customer mapCustomerDtoToCustomer(CustomerDto customerDto) {
    return customerMapper.mapCustomerDtoToCustomer(customerDto);
  }

  public CustomerDto mapCustomerToCustomerDto(Customer customer) {
    return customerMapper.mapCustomerToCustomerDto(customer);
  }

  public Product mapProductDtoToProduct(ProductDto productDto) {
    return productMapper.mapProductDtoToProduct(productDto);
  }

  public ProductDto mapProductToProductDto(Product product) {
    return productMapper.mapProductToProductDto(product);
  }

  public String encodePassword(String password) {
    return passwordEncoder.encode(password);
  }

  public boolean doPasswordMatches(String passwordFromForm, String passwordFromDb) {
    return passwordEncoder.matches(passwordFromForm, passwordFromDb);
  }
}

