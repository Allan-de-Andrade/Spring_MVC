package com.allan.SpringMVC.controllers;

import com.allan.SpringMVC.models.DTOs.UserDTO;
import com.allan.SpringMVC.models.Entities.User;
import com.allan.SpringMVC.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView loginPage(@AuthenticationPrincipal @ModelAttribute User user){
        ModelAndView modelAndView = new ModelAndView("/login");
        modelAndView.addObject("user",user);
        return modelAndView;
    }

    @GetMapping("/signup")
    public String signUpPage(){
        return "/signUp";
    }

    @GetMapping("/sucesslogin")
    public String sucessLogin(){
        return "redirect:task/list";
    }
    @PostMapping("/signup")
    public String signUp(@ModelAttribute UserDTO userDTO){

        if(!userDTO.getPassword().isEmpty() && !userDTO.getUsername().isEmpty())
          userService.saveUser(userDTO);

        return "redirect:login";
    }
}
