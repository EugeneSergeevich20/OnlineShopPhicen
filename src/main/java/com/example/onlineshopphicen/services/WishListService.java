package com.example.onlineshopphicen.services;

import com.example.onlineshopphicen.model.Cart;
import com.example.onlineshopphicen.model.User;
import com.example.onlineshopphicen.model.Wishlist;
import com.example.onlineshopphicen.repositories.WishListRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;

@Service
public class WishListService {

    private final WishListRepository wishListRepository;

    public WishListService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    @Transactional
    public void createWishlist(User user){
        Wishlist wishlist = Wishlist.builder()
                .user(user)
                .products(new HashSet<>())
                .build();
        wishListRepository.save(wishlist);
    }

}
