package com.microservices.ProductService.model;

import lombok.Data;

@Data
public class ProductRequestDto {
    private Long price;
    private String productName;
    private Long quantity;
}
