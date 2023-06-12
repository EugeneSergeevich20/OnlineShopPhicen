package com.example.onlineshopphicen.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String sculpt;
    private String hairColor;
    private String bodyType;
    private String skinTone;
    private String edition;
    private String additionallyInfo;
    private BigDecimal price;
    private int quantity;
    @CreationTimestamp
    private LocalDateTime addDate;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ImageProduct> imageProducts;

    @ManyToMany(mappedBy = "products")
    private List<OrderDetails> orderDetails;

    @ManyToMany(mappedBy = "products")
    private List<Cart> carts;

    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private List<Wishlist> wishlists;

    public ImageProduct getImage(){
        if (!imageProducts.isEmpty())
            return imageProducts.get(0);
        else
            return null;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return quantity == product.quantity && Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(sculpt, product.sculpt) && Objects.equals(hairColor, product.hairColor) && Objects.equals(bodyType, product.bodyType) && Objects.equals(skinTone, product.skinTone) && Objects.equals(edition, product.edition) && Objects.equals(additionallyInfo, product.additionallyInfo) && Objects.equals(price, product.price) && Objects.equals(addDate, product.addDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sculpt, hairColor, bodyType, skinTone, edition, additionallyInfo, price, quantity, addDate);
    }*/
}
