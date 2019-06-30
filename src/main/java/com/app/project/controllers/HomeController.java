package com.app.project.controllers;

import com.app.project.dto.CustomerDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping("/")
  public String redirectToHome(){
    return "redirect:/home";
  }

  @GetMapping("/home")
  public String home(Model model) {
    model.addAttribute("userRegister", new CustomerDto());
    model.addAttribute("userLogin", new CustomerDto());
    return "home";
  }

  @GetMapping("/logout")
  public String logOut() {
    return "redirect:/home";
  }
}
