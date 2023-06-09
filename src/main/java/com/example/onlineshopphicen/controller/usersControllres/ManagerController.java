package com.example.onlineshopphicen.controller.usersControllres;

import com.example.onlineshopphicen.model.Order;
import com.example.onlineshopphicen.model.OrderStatus;
import com.example.onlineshopphicen.model.Product;
import com.example.onlineshopphicen.services.OrderService;
import com.example.onlineshopphicen.services.productService.ImageService;
import com.example.onlineshopphicen.services.productService.ProductService;
import com.example.onlineshopphicen.services.usersService.UserDetailsService;
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
    private final UserDetailsService userDetailsService;
    private final OrderService orderService;

    @Autowired
    public ManagerController(ProductService productService, ImageService imageService, UserDetailsService userDetailsService, OrderService orderService) {
        this.productService = productService;
        this.imageService = imageService;
        this.userDetailsService = userDetailsService;
        this.orderService = orderService;
    }

    @GetMapping("/products")
    public String showAllProducts(Model model){
        model.addAttribute("products", productService.findAllProducts());
        model.addAttribute("userAuth", userDetailsService.getAuthUser());
        return "/product/manager/show_all_products";
    }

    @GetMapping("/product/add")
    public String addProduct(@ModelAttribute("product")Product product, Model model, @ModelAttribute("image") List<MultipartFile> image){
        model.addAttribute("userAuth", userDetailsService.getAuthUser());
        return "/product/manager/add_product";
    }

    @PostMapping("/products")
    public String saveProduct(@ModelAttribute("product") Product product, @RequestParam("image") List<MultipartFile> image) throws IOException {
        productService.addProduct(product, image);
        return "redirect:/manager/products";
    }

    @GetMapping("/product/{id}")
    public String showProduct(Model model, @PathVariable Long id, @ModelAttribute("image") List<MultipartFile> image){

        Product product = productService.findProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("userAuth", userDetailsService.getAuthUser());
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

    @GetMapping("/orders")
    public String showAllOrders(Model model, @ModelAttribute("orderStatus") Order order){
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        model.addAttribute("userAuth", userDetailsService.getAuthUser());
        model.addAttribute("statusOrder", OrderStatus.values());
        return "/product/manager/show_all_orders";
    }

    @PatchMapping("/orders/status/{id}")
    public String updateStatus(@PathVariable Long id, @ModelAttribute("orderStatus") Order order){
        orderService.updateStatus(order, id);
        return "redirect:/manager/orders";
    }

    @GetMapping("/action")
    public String managerPage(Model model){
        model.addAttribute("userAuth", userDetailsService.getAuthUser());
        return "/product/manager/manager";
    }

}
