package com.example.onlineshopphicen.services;

import com.example.onlineshopphicen.model.Cart;
import com.example.onlineshopphicen.model.Product;
import com.example.onlineshopphicen.model.User;
import com.example.onlineshopphicen.repositories.CartRepository;
import com.example.onlineshopphicen.repositories.productRepository.ProductRepository;
import com.example.onlineshopphicen.services.productService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final EntityManager entityManager;

    @Autowired
    public CartService(CartRepository cartRepository, ProductRepository productRepository, ProductService productService, EntityManager entityManager) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.productService = productService;
        this.entityManager = entityManager;
    }

    public Cart findByUser(User user){
        return cartRepository.findByUser(user).get();
    }

    public Cart findId(Long id){
        return cartRepository.findById(id).get();
    }

    @Transactional
    public void createCart(User user){
        Cart cart = Cart.builder()
                .user(user)
                .products(new ArrayList<>())
                .build();
        cartRepository.save(cart);
    }

    @Transactional
    public void addProductToCart(Cart cart, Long productId){
        Product product = productService.findProductById(productId);
        product.getCarts().add(cart);
        cart.getProducts().add(product);
        cartRepository.save(cart);
    }

    @Transactional
    public void deleteProduct(Cart cart, Product product){
        cart.getProducts().remove(product);
        cartRepository.save(cart);
    }

    @Transactional
    public List<Product> getProducts(Cart cart){
        return cart.getProducts();
    }

}
