package com.example.onlineshopphicen.controller.productController;

import com.example.onlineshopphicen.model.Cart;
import com.example.onlineshopphicen.model.DeliveryMethod;
import com.example.onlineshopphicen.model.Order;
import com.example.onlineshopphicen.model.User;
import com.example.onlineshopphicen.services.CartService;
import com.example.onlineshopphicen.services.OrderDetailsService;
import com.example.onlineshopphicen.services.OrderService;
import com.example.onlineshopphicen.services.usersService.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderDetailsService orderDetailsService;
    private final OrderService orderService;
    private final UserDetailsService userDetailsService;
    private final CartService cartService;

    public OrderController(OrderDetailsService orderDetailsService, OrderService orderService, UserDetailsService userDetailsService, CartService cartService) {
        this.orderDetailsService = orderDetailsService;
        this.orderService = orderService;
        this.userDetailsService = userDetailsService;
        this.cartService = cartService;
    }

    @GetMapping("")
    public String createOrder(Model model, @ModelAttribute("order") Order order){
        User user = userDetailsService.getAuthUser();
        Cart cart = cartService.findId(user.getCart().getId());
        model.addAttribute("userAuth", userDetailsService.getAuthUser());
        model.addAttribute("products", cart);
        model.addAttribute("delivery", DeliveryMethod.values());
        return "order";
    }

    @PostMapping("/createOrder/{id}")
    public String createNewOrder(@ModelAttribute("order")Order order, @PathVariable("id") Long id){
        User user = userDetailsService.findById(id);
        Cart cart = cartService.findId(user.getCart().getId());
        orderService.createOrder(user, order, cart);
        return "redirect:/home";
    }

}
