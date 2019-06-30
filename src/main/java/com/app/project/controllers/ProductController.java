package com.app.project.controllers;

import com.app.project.dto.CustomerDto;
import com.app.project.dto.ProductDto;
import com.app.project.exceptions.NotValidProductException;
import com.app.project.model.entity.Customer;
import com.app.project.model.entity.Product;
import com.app.project.service.ProductService;
import com.app.project.utils.EmailUtil;
import com.app.project.utils.GlobalControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
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

  @GetMapping("/products")
  public String login(Model model, @ModelAttribute(name = "userLogin") CustomerDto customerDto) {

    model.addAttribute("product", new ProductDto());
    globalControllerUtil.setModelAttributes(model, customerDto);

    return "logged";
  }

  @PostMapping(value = "addProduct")
  public String addProduct(HttpSession session, Model model, @ModelAttribute(value = "product") @Valid ProductDto productDto, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      throw new NotValidProductException("Invalid product");
    }

    Product product = globalControllerUtil.mapProductDtoToProduct(productDto);
    CustomerDto customerDto = (CustomerDto) session.getAttribute("userLogin");
    Customer customer = globalControllerUtil.mapCustomerDtoToCustomer(customerDto);
    product.setCustomer(customer);

    globalControllerUtil.setModelAttributes(model, customerDto);

    productService.addProduct(product);
    return "redirect:/products";
  }

  @GetMapping(value = "deleteProduct/{id}")
  public String deleteProduct(@PathVariable Integer id) {

    productService.deleteProductById(id);
    return "redirect:/products";
  }

  @GetMapping("/mail")
  public String sendMail(HttpSession session) {

    List<Product> customerProducts = productService.getCustomerProductsList(((CustomerDto) session.getAttribute("userLogin")).getEmailAddress());
    EmailUtil.sendAllSummaryTable(((CustomerDto) session.getAttribute("userLogin")).getEmailAddress(), "Twoje zakupy", customerProducts);

    return "redirect:/products";
  }

  @GetMapping("/endSession")
  public String endSession(SessionStatus sessionStatus) {
    sessionStatus.setComplete();
    return "redirect:/logout";
  }
}
