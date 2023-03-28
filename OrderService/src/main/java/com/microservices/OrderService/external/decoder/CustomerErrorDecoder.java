package com.microservices.OrderService.external.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.OrderService.exception.OrderException;
import com.microservices.OrderService.external.response.ErrorResponse;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;

public class CustomerErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {

        ObjectMapper objectMapper= new ObjectMapper();

        try {
            ErrorResponse errorResponse= objectMapper.readValue(response.body().asInputStream(),ErrorResponse.class);
             return new OrderException(errorResponse.getErrorMessage(),errorResponse.getErrorCode());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
