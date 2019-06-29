package com.app.project.controllers;

import com.app.project.dto.CustomerDto;
import com.app.project.exceptions.CustomerAlreadyExists;
import com.app.project.exceptions.NotValidInputException;
import com.app.project.model.entity.Customer;
import com.app.project.service.CustomerService;
import com.app.project.utils.GlobalControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
      throw new NotValidInputException("Niepoprawne dane");
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

  @RequestMapping("/log")
  public String login(@ModelAttribute(name = "userLogin") CustomerDto customerDto, RedirectAttributes redirectAttributes) {


    CustomerDto customerDtoFromDb = customerService.getUserByEmail(customerDto.getEmailAddress());
    if (customerDtoFromDb == null) {
      return "userNotFound";
    }

    redirectAttributes.addFlashAttribute("userLogin", customerDtoFromDb);
    return globalControllerUtil.doPasswordMatches(customerDto.getPassword(), customerDtoFromDb.getPassword()) ?
            "redirect:/products" : "bad_password";
  }

  @RequestMapping("/delete")
  public String deleteAccount(HttpSession session, SessionStatus sessionStatus) {

    customerService.deleteCustomerById(((CustomerDto) session.getAttribute("userLogin")).getId());
    session.removeAttribute("userLogin");
    sessionStatus.setComplete();
    return "redirect:/home";
  }

}
