package com.microservices.ProductService.controller;

import com.microservices.ProductService.model.ProductRequestDto;
import com.microservices.ProductService.model.ProductResponseDto;
import com.microservices.ProductService.service.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    ProductController(ProductService productService){

        this.productService=productService;

    }

    @PostMapping("/add")
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequestDto productRequestDto){

        Long productId= productService.addProduct(productRequestDto);

        return new ResponseEntity<>(productId, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable("id")  Long productId){

        ProductResponseDto productResponseDto= productService.getProductById(productId);

        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    @PutMapping("/reduce-quantity/{id}")
    ResponseEntity<Void> reduceProductQuantity(@PathVariable("id") Long productId,@RequestParam Long quantity){

        productService.reduceProductQuantity(productId,quantity);

        return new  ResponseEntity<>(HttpStatus.OK);
    }


}
