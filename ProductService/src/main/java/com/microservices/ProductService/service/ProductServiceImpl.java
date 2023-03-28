package com.microservices.ProductService.service;

import com.microservices.ProductService.entity.Product;
import com.microservices.ProductService.exception.ProductException;
import com.microservices.ProductService.model.ProductRequestDto;
import com.microservices.ProductService.model.ProductResponseDto;
import com.microservices.ProductService.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;


    ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Long addProduct(ProductRequestDto productRequestDto) {
        Product product = Product.builder().productName(productRequestDto.getProductName()).price(productRequestDto.getPrice()).quantity(productRequestDto.getQuantity()).build();
        productRepository.save(product);
        return product.getProductId();
    }

    @Override
    public ProductResponseDto getProductById(Long productId) {

        Product product = productRepository.findById(productId).orElseThrow(() ->  new ProductException("Product Not Found With Given Id","404"));

        ProductResponseDto productResponseDto = new ProductResponseDto();

        BeanUtils.copyProperties(product, productResponseDto);

        return productResponseDto;

    }

    @Override
    public void reduceProductQuantity(Long productId,Long quantity) {

        Product product= productRepository.findById(productId).orElseThrow(()->new ProductException("Product With Given Id Not Found","404"));

        if(product.getQuantity()<quantity) throw new ProductException("The Given Quantity is More than stock present","400");

        product.setQuantity(product.getQuantity()-quantity);
        productRepository.save(product);
    }
}
