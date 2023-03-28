package com.microservices.OrderService.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class OrderException extends RuntimeException{

    public String errorCode;

    public OrderException(String message, String errorCode){
        super(message);
        this.errorCode=errorCode;
    }
}
