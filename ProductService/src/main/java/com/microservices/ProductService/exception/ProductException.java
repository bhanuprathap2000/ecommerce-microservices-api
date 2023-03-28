package com.microservices.ProductService.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ProductException extends RuntimeException{

    public String errorCode;

    public ProductException(String message, String errorCode){
        super(message);
        this.errorCode=errorCode;
    }
}
