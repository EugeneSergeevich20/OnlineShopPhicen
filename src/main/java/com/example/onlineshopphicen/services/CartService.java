package com.example.onlineshopphicen.services;

import com.example.onlineshopphicen.model.Cart;
import com.example.onlineshopphicen.model.Product;
import com.example.onlineshopphicen.model.User;
import com.example.onlineshopphicen.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Transactional
    public void createCart(User user){
        Cart cart = Cart.builder()
                .user(user)
                .products(new ArrayList<>())
                .build();
        cartRepository.save(cart);
    }

    /*@Transactional
    public void addProductToCart(User user, Product product){
        Cart cart;

        *//*if (user.getCart() == null){
            cart = new Cart();
        }
        else {
            cart = user.getCart();
        }*//*

        cart.addProduct(product);

        cartRepository.save(cart);
    }*/

}
