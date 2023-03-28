package com.microservices.ApiGateway.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceFallbackController {

    @GetMapping("/order-service-fallback")
    ResponseEntity<String> orderServiceFallback(){

        return new ResponseEntity<>("Order Service Is Down For Some Reason", HttpStatus.SERVICE_UNAVAILABLE);
    }

    @GetMapping("/product-service-fallback")
    ResponseEntity<String> productServiceFallback(){

        return new ResponseEntity<>("Product Service Is Down For Some Reason", HttpStatus.SERVICE_UNAVAILABLE);
    }

    @GetMapping("/payment-service-fallback")
    ResponseEntity<String> paymentServiceFallback(){

        return new ResponseEntity<>("Payment Service Is Down For Some Reason", HttpStatus.SERVICE_UNAVAILABLE);
    }

}
