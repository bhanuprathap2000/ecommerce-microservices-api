package com.microservices.ProductService.exception;

import com.microservices.ProductService.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class RestResponseExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductException.class)
    ResponseEntity<ErrorResponse> handleProductServiceException(ProductException productException){

        if (Objects.equals(productException.getErrorCode(), "404")) {
            return new ResponseEntity<>(new ErrorResponse(productException.getMessage(), productException.getErrorCode()), HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(new ErrorResponse(productException.getMessage(), productException.getErrorCode()), HttpStatus.BAD_REQUEST);

        }

    }
}
