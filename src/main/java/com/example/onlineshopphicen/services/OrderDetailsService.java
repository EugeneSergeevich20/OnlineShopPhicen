package com.example.onlineshopphicen.services;

import com.example.onlineshopphicen.model.Cart;
import com.example.onlineshopphicen.model.Order;
import com.example.onlineshopphicen.model.OrderDetails;
import com.example.onlineshopphicen.model.Product;
import com.example.onlineshopphicen.repositories.OrderDetailsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;
    private final CartService cartService;

    public OrderDetailsService(OrderDetailsRepository orderDetailsRepository, CartService cartService) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.cartService = cartService;
    }

    public void createOrderDetails(Cart cart, Order order){
        List<Product> products = cartService.getProducts(cart);

        OrderDetails orderDetails = OrderDetails.builder()
                .order(order)
                .products(new ArrayList<>())
                .build();

        for (Product product : products){
            orderDetails.getProducts().add(product);
        }

        BigDecimal totalPrice = BigDecimal.valueOf(orderDetails.getTotalPrice());

        order.setOrderTotal(totalPrice);
        orderDetailsRepository.save(orderDetails);
    }

}
