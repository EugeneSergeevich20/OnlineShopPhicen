package com.example.onlineshopphicen.services;

import com.example.onlineshopphicen.model.Cart;
import com.example.onlineshopphicen.model.Product;
import com.example.onlineshopphicen.model.User;
import com.example.onlineshopphicen.model.Wishlist;
import com.example.onlineshopphicen.repositories.WishListRepository;
import com.example.onlineshopphicen.services.productService.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class WishListService {

    private final WishListRepository wishListRepository;
    private final ProductService productService;

    public WishListService(WishListRepository wishListRepository, ProductService productService) {
        this.wishListRepository = wishListRepository;
        this.productService = productService;
    }

    public Wishlist findByUser(User user){
        return wishListRepository.findByUser(user).get();
    }

    public Wishlist findId(Long id){
        return wishListRepository.findById(id).get();
    }

    @Transactional
    public void createWishlist(User user){
        Wishlist wishlist = Wishlist.builder()
                .user(user)
                .products(new ArrayList<>())
                .build();
        wishListRepository.save(wishlist);
    }

    @Transactional
    public void addProduct(Wishlist wishlist, Long productId){
        Product product = productService.findProductById(productId);
        product.getWishlists().add(wishlist);
        wishlist.getProducts().add(product);
        wishListRepository.save(wishlist);
    }

    @Transactional
    public void deleteProduct(Wishlist wishlist, Product product){
        wishlist.getProducts().remove(product);
        wishListRepository.save(wishlist);
    }

}
