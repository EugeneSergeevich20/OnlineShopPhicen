package com.example.onlineshopphicen.controller;

import com.example.onlineshopphicen.model.User;
import com.example.onlineshopphicen.services.usersService.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final UserDetailsService userDetailsService;

    @Autowired
    public HomeController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/home")
    public String home(Model model){

        model.addAttribute("userAuth", userDetailsService.getAuthUser());

        return "index";
    }

    @GetMapping("/hello")
    public String hello(Model model){

        return "hello";
    }

    @GetMapping("/showUserInfo")
    public String showUserInfo(){
        User user = userDetailsService.getAuthUser();
        System.out.println(user);

        return "index";
    }


}
