package com.microservices.PaymentService.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErrorResponse {
    String errorMessage;
    String errorCode;
}
