package com.microservices.ProductService.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name="product_id")
    private Long productId;
    @Column(name="product_name")
    private String productName;
    @Column(name="price")
    private Long price;
    @Column(name="quantity")
    private Long quantity;

}
