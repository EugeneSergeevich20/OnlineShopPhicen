package com.example.onlineshopphicen.repositories.productRepository;

import com.example.onlineshopphicen.model.ImageProduct;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<ImageProduct, Long> {

    List<ImageProduct> findImageProductByProductId(Long id);

}
