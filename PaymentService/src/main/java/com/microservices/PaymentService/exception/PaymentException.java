package com.microservices.PaymentService.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PaymentException extends RuntimeException{

    public String errorCode;

    public PaymentException(String message, String errorCode){
        super(message);
        this.errorCode=errorCode;
    }
}
