package com.microservices.OrderService.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="PRODUCT-SERVICE/product")
public interface ProductService {

    @PutMapping("/reduce-quantity/{id}")
    void reduceProductQuantity(@PathVariable("id") Long productId, @RequestParam Long quantity);
}
