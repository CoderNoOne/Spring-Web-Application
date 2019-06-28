package com.app.project.controllers;

import com.app.project.dto.CustomerDto;
import com.app.project.dto.ProductDto;
import com.app.project.exceptions.NotValidProductException;
import com.app.project.model.Customer;
import com.app.project.model.Product;
import com.app.project.service.ProductService;
import com.app.project.utils.EmailUtil;
import com.app.project.utils.GlobalControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.List;

@Controller
@SessionAttributes("userLogin")
public class ProductController {

  private ProductService productService;
  private GlobalControllerUtil globalControllerUtil;

  @Autowired
  public ProductController(ProductService productService, GlobalControllerUtil globalControllerUtil) {
    this.productService = productService;
    this.globalControllerUtil = globalControllerUtil;
  }

  @RequestMapping("/products")
  public String login(Model model, @ModelAttribute(name = "userLogin") CustomerDto customerDto) {

    model.addAttribute("product", new ProductDto());
    globalControllerUtil.setModelAttributes(model, customerDto, customerDto.getEmailAddress());

    return "logged";
  }

  @RequestMapping(value = "addProduct/{email}")
  public String addProduct(Model model, @ModelAttribute(value = "userLogin") CustomerDto customerDto, @PathVariable String email, @ModelAttribute(value = "product") @Valid ProductDto productDto, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      throw new NotValidProductException("Invalid product");
    }

    Product product = globalControllerUtil.mapProductDtoToProduct(productDto);
    Customer customer = globalControllerUtil.mapCustomerDtoToCustomer(customerDto);
    product.setCustomer(customer);

    globalControllerUtil.setModelAttributes(model, customerDto, email);

    productService.addProduct(product);
    return "redirect:/products";
  }

  @RequestMapping("/mail")
  public String sendMail(@ModelAttribute(name = "userLogin") CustomerDto customerDto) {

    List<Product> customerProducts = productService.getCustomerProductsList(customerDto.getEmailAddress());
    EmailUtil.sendAllSummaryTable(customerDto.getEmailAddress(), "Twoje zakupy", customerProducts);

    return "redirect:/products";
  }

  @RequestMapping("/endSession")
  public String endSession(SessionStatus sessionStatus) {
    sessionStatus.setComplete();
    return "redirect:/logout";
  }

}
