package com.example.onlineshopphicen.controller.usersControllres;

import com.example.onlineshopphicen.model.User;
import com.example.onlineshopphicen.services.CartService;
import com.example.onlineshopphicen.services.WishListService;
import com.example.onlineshopphicen.services.usersService.RegistrationService;
import com.example.onlineshopphicen.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserValidator userValidator;
    private final RegistrationService registrationService;
    private final CartService cartService;
    private final WishListService wishListService;

    @Autowired
    public AuthController(UserValidator userValidator, RegistrationService registrationService, CartService cartService, WishListService wishListService) {
        this.userValidator = userValidator;
        this.registrationService = registrationService;
        this.cartService = cartService;
        this.wishListService = wishListService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "/auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") User user){
        return "/auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("user") @Valid User user,
                                      BindingResult bindingResult){

        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()){
            return "/auth/registration";
        }

        registrationService.register(user);
        cartService.createCart(user);
        wishListService.createWishlist(user);

        return "redirect:/auth/login";
    }

}
