package com.example.onlineshopphicen.controller.usersControllres;

import com.example.onlineshopphicen.model.User;
import com.example.onlineshopphicen.services.CartService;
import com.example.onlineshopphicen.services.usersService.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientController {

    private final UserDetailsService userDetailsService;

    @Autowired
    public ClientController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/cart")
    public String getCartPage(Model model){
        model.addAttribute("cart", userDetailsService.getUserCart());
        return "cart";
    }
}
