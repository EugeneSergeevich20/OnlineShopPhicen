package com.example.onlineshopphicen.repositories;

import com.example.onlineshopphicen.model.Cart;
import com.example.onlineshopphicen.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<Cart> findByCartId(Long id);

}
