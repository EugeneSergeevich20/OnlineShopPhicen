package com.example.onlineshopphicen.repositories.productRepository;

import com.example.onlineshopphicen.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
