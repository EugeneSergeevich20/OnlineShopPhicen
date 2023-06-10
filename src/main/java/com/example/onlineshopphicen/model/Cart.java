package com.example.onlineshopphicen.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "carts_products",
            joinColumns = @JoinColumn(name = "carts_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    public double getTotalPrice(){
        List<Product> productList = this.getProducts();
        double totalPrice = 0;

        if (productList != null) {
            for (Product product : productList) {
                totalPrice += product.getPrice().doubleValue();
            }
        }
        return totalPrice;
    }

    public void addProduct(Product product){
        this.products.add(product);
    }

}
