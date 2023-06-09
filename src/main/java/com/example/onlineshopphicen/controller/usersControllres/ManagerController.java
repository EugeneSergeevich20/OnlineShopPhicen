package com.example.onlineshopphicen.controller.usersControllres;

import com.example.onlineshopphicen.model.Product;
import com.example.onlineshopphicen.services.productService.ImageService;
import com.example.onlineshopphicen.services.productService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    private final ProductService productService;
    private final ImageService imageService;

    @Autowired
    public ManagerController(ProductService productService, ImageService imageService) {
        this.productService = productService;
        this.imageService = imageService;
    }

    @GetMapping("/products")
    public String showAllProducts(Model model){
        model.addAttribute("products", productService.findAllProducts());
        return "/product/manager/show_all_products";
    }

    @GetMapping("/product/add")
    public String addProduct(@ModelAttribute("product")Product product){
        return "/product/manager/add";
    }

    @PostMapping("/products")
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.addProduct(product);
        return "redirect:/manager/products";
    }

    @GetMapping("/product/{id}")
    public String showProduct(Model model, @PathVariable Long id, @ModelAttribute("image") List<MultipartFile> image){

        Product product = productService.findProductById(id);
        model.addAttribute("product", product);



        return "/product/manager/show_info_product";
    }

    @PostMapping("/product/{id}")
    public String addImageProduct(@RequestParam("image") List<MultipartFile> image, @PathVariable Long id) throws IOException {
        Product product = productService.findProductById(id);
        imageService.save(image, product);
        return "redirect:/manager/products";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        productService.delete(id);
        return "redirect:/manager/products";
    }

}
