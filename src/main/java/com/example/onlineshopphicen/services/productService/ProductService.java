package com.example.onlineshopphicen.services.productService;

import com.example.onlineshopphicen.model.Product;
import com.example.onlineshopphicen.repositories.productRepository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    @Transactional
    public Product findProductById(Long id){
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @Transactional
    public void addProduct(Product product){
        productRepository.save(product);
    }

    @Transactional
    public void delete(Long id){
        productRepository.deleteById(id);
    }

}
