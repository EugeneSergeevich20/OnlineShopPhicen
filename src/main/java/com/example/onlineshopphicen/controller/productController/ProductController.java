package com.example.onlineshopphicen.controller.productController;

import com.example.onlineshopphicen.model.Product;
import com.example.onlineshopphicen.services.CartService;
import com.example.onlineshopphicen.services.productService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/catalog")
public class ProductController {

    private final ProductService productService;
    private final CartService cartService;

    @Autowired
    public ProductController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @GetMapping("")
    public String showAllProduct(Model model,
                                 @RequestParam("page") Optional<Integer> page,
                                 @RequestParam("size") Optional<Integer> size){

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(2);

        Page<Product> productPage = productService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        //model.addAttribute("products", productService.findAllProducts());
        model.addAttribute("products", productPage);

        int totalPages = productPage.getTotalPages();
        if (totalPages > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "/catalog/catalog";
    }

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable Long id, Model model){
        model.addAttribute("product", productService.findProductById(id));
        return "/product/about_product";
    }




}
