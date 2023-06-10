package com.example.onlineshopphicen.repositories;

import com.example.onlineshopphicen.model.Cart;
import com.example.onlineshopphicen.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}
