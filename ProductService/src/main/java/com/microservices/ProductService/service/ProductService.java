package com.microservices.ProductService.service;

import com.microservices.ProductService.model.ProductRequestDto;
import com.microservices.ProductService.model.ProductResponseDto;

public interface ProductService {
    Long addProduct(ProductRequestDto productRequestDto);

    ProductResponseDto getProductById(Long productId);

    void reduceProductQuantity(Long productId,Long quantity);
}
