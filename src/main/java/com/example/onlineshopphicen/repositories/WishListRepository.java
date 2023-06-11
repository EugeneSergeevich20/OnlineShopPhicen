package com.example.onlineshopphicen.repositories;

import com.example.onlineshopphicen.model.User;
import com.example.onlineshopphicen.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishListRepository extends JpaRepository<Wishlist, Long> {

    Optional<Wishlist> findById(Long id);
    Optional<Wishlist> findByUser(User user);

}
