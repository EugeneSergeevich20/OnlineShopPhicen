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

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<OrderDetails> orderDetails;

    @ManyToMany(mappedBy = "products")
    private List<Cart> carts;

    @OneToMany(mappedBy = "product")
    private List<Wishlist> wishlists;

    public ImageProduct getImage(){
        if (!imageProducts.isEmpty())
            return imageProducts.get(0);
        else
            return null;
    }

}
