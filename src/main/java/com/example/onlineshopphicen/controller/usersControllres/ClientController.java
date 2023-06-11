package com.example.onlineshopphicen.controller.usersControllres;

import com.example.onlineshopphicen.model.Cart;
import com.example.onlineshopphicen.model.Product;
import com.example.onlineshopphicen.model.User;
import com.example.onlineshopphicen.services.CartService;
import com.example.onlineshopphicen.services.WishListService;
import com.example.onlineshopphicen.services.productService.ProductService;
import com.example.onlineshopphicen.services.usersService.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClientController {

    private final UserDetailsService userDetailsService;
    private final CartService cartService;
    private final ProductService productService;
    private final WishListService wishListService;

    @Autowired
    public ClientController(UserDetailsService userDetailsService, CartService cartService, ProductService productService, WishListService wishListService) {
        this.userDetailsService = userDetailsService;
        this.cartService = cartService;
        this.productService = productService;
        this.wishListService = wishListService;
    }

    @GetMapping("/cart")
    public String getCartPage(Model model){
        User user = userDetailsService.getAuthUser();
        Cart cart = cartService.findId(user.getCart().getId());
        model.addAttribute("userAuth", userDetailsService.getAuthUser());
        model.addAttribute("cart", cart);
        return "cart";
    }

    @PostMapping("/addCart/{idProduct}")
    public String addProductToCart(@PathVariable Long idProduct){
        Cart cart = cartService.findByUser(userDetailsService.getAuthUser());
        cartService.addProductToCart(cart, idProduct);
        return "redirect:/catalog";
    }

    @DeleteMapping("/deleteCart/{id}")
    public String deleteProductCart(@PathVariable Long id){
        Product product = productService.findProductById(id);
        Cart cart = cartService.findByUser(userDetailsService.getAuthUser());
        cartService.deleteProduct(cart, product);
        return "redirect:/cart";
    }

    @GetMapping("/account")
    public String account(Model model){
        model.addAttribute("account", userDetailsService.getAuthUser());
        return "account";
    }

    @GetMapping("/favorites")
    public String getFavoritesPage(Model model){
        model.addAttribute("wishlist", userDetailsService.getUserWishList());
        return "favorites";
    }

    @PostMapping("/addWishlist/{id}")
    public String addProductToWishList(@PathVariable Long id){
        Product product = productService.findProductById(id);
        wishListService.addProduct(userDetailsService.getUserWishList(), product);
        return "redirect:/catalog";
    }


}
