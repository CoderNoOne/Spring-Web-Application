package com.app.project.controllers;

import com.app.project.dto.CustomerDto;
import com.app.project.exceptions.CustomerAlreadyExists;
import com.app.project.exceptions.NotValidInputException;
import com.app.project.exceptions.UserNotFoundException;
import com.app.project.model.entity.Customer;
import com.app.project.service.CustomerService;
import com.app.project.utils.GlobalControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@Controller
public class CustomerController {

  private CustomerService customerService;
  private GlobalControllerUtil globalControllerUtil;

  @Autowired
  public CustomerController(CustomerService customerService, GlobalControllerUtil globalControllerUtil) {
    this.customerService = customerService;
    this.globalControllerUtil = globalControllerUtil;
  }

  @PostMapping("/save")
  public String save(Model model, @ModelAttribute(value = "userRegister") @Valid CustomerDto customerDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new NotValidInputException("bad input", Map.of("saveUserErrors", bindingResult.getAllErrors()));
    }

    customerDto.setPassword(globalControllerUtil.encodePassword(customerDto.getPassword()));
    Customer customer = globalControllerUtil.mapCustomerDtoToCustomer(customerDto);
    if (customerService.checkIfCustomerIsInDb(customer)) {
      throw new CustomerAlreadyExists("Użytkownik z podanym emailem juz istnieje w bazie danych");
    }

    customerService.saveCustomerIntoDb(customer);
    Integer totalNumberOfRegisteredCustomers = customerService.getTotalNumberOfRegisteredCustomers();
    model.addAttribute("totalUsers", totalNumberOfRegisteredCustomers);

    return "saved";
  }

  @PostMapping("/log")
  public String login(@ModelAttribute(name = "userLogin") CustomerDto customerDto, RedirectAttributes redirectAttributes) {

    CustomerDto customerDtoFromDb = customerService.getUserByEmail(customerDto.getEmailAddress());
    if (customerDtoFromDb == null) {
      throw new UserNotFoundException("User doesn't exist in our DB yet!");
    }

    redirectAttributes.addFlashAttribute("userLogin", customerDtoFromDb);
    return globalControllerUtil.doPasswordMatches(customerDto.getPassword(), customerDtoFromDb.getPassword()) ?
            "redirect:/products" : "bad_password";
  }

  @GetMapping("/delete")
  public String deleteAccount(HttpSession session, SessionStatus sessionStatus) {

    customerService.deleteCustomerById(((CustomerDto) session.getAttribute("userLogin")).getId());
    session.removeAttribute("userLogin");
    sessionStatus.setComplete();
    return "redirect:/home";
  }

}
