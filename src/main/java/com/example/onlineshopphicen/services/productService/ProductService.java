package com.example.onlineshopphicen.services.productService;

import com.example.onlineshopphicen.model.Product;
import com.example.onlineshopphicen.repositories.productRepository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    public Page<Product> findPaginated(Pageable pageable){

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Product> products = productRepository.findAll();

        List<Product> list;

        if (products.size() < startItem){
            list = Collections.emptyList();
        }
        else {
            int toIndex = Math.min(startItem + pageSize, products.size());
            list = products.subList(startItem, toIndex);
        }

        Page<Product> productPage = new PageImpl<Product>(list, PageRequest.of(currentPage, pageSize), products.size());

        return productPage;
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

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @Transactional
    public void delete(Long id){
        productRepository.deleteById(id);
    }

}
