package com.microservices.OrderService.exception;

import com.microservices.OrderService.external.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class RestResponseExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(OrderException.class)
    ResponseEntity<ErrorResponse> handleOrderServiceException(OrderException orderException){

        if (Objects.equals(orderException.getErrorCode(), "404")) {
            return new ResponseEntity<>(new ErrorResponse(orderException.getMessage(), orderException.getErrorCode()), HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(new ErrorResponse(orderException.getMessage(), orderException.getErrorCode()), HttpStatus.BAD_REQUEST);

        }

    }
}
