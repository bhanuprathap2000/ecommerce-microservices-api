package com.microservices.PaymentService.exception;

import com.microservices.PaymentService.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class RestResponseExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PaymentException.class)
    ResponseEntity<ErrorResponse> handlePaymentException(PaymentException paymentException){

        if (Objects.equals(paymentException.getErrorCode(), "404")) {
            return new ResponseEntity<>(new ErrorResponse(paymentException.getMessage(), paymentException.getErrorCode()), HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(new ErrorResponse(paymentException.getMessage(), paymentException.getErrorCode()), HttpStatus.BAD_REQUEST);

        }

    }
}
