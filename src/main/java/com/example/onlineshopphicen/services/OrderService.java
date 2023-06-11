package com.example.onlineshopphicen.services;

import com.example.onlineshopphicen.model.*;
import com.example.onlineshopphicen.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailsService orderDetailsService;

    public OrderService(OrderRepository orderRepository, OrderDetailsService orderDetailsService) {
        this.orderRepository = orderRepository;
        this.orderDetailsService = orderDetailsService;
    }

    @Transactional
    public void createOrder(User user, Order order, Cart cart){

        Order orderNew = Order.builder()
                .address(order.getAddress())
                .status(OrderStatus.NEW)
                .user(user)
                .build();

        orderRepository.save(orderNew);
        orderDetailsService.createOrderDetails(cart, orderNew);
    }

}
