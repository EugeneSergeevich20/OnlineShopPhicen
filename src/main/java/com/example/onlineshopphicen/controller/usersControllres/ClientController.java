package com.example.onlineshopphicen.controller.usersControllres;

import com.example.onlineshopphicen.model.Product;
import com.example.onlineshopphicen.services.CartService;
import com.example.onlineshopphicen.services.productService.ProductService;
import com.example.onlineshopphicen.services.usersService.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClientController {

    private final UserDetailsService userDetailsService;
    private final CartService cartService;
    private final ProductService productService;

    @Autowired
    public ClientController(UserDetailsService userDetailsService, CartService cartService, ProductService productService) {
        this.userDetailsService = userDetailsService;
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping("/cart")
    public String getCartPage(Model model){
        model.addAttribute("cart", userDetailsService.getUserCart());
        return "cart";
    }

    @PostMapping("/add/{id}")
    public String addProductToCart(@PathVariable Long id){
        Product product = productService.findProductById(id);
        cartService.addProductToCart(userDetailsService.getUserCart(), product);
        return "redirect:/catalog";
    }
}
