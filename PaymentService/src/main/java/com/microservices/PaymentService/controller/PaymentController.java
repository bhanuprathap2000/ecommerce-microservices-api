package com.microservices.PaymentService.controller;

import com.microservices.PaymentService.model.PaymentRequestDto;
import com.microservices.PaymentService.model.PaymentResponseDto;
import com.microservices.PaymentService.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    PaymentController(PaymentService paymentService){

        this.paymentService=paymentService;

    }


    @PostMapping
    ResponseEntity<Long> doPayment(@RequestBody PaymentRequestDto paymentRequestDto){

        Long paymentId= paymentService.doPayment(paymentRequestDto);

        return new ResponseEntity<>(paymentId, HttpStatus.OK);
    }

    @GetMapping("/{orderId")
    ResponseEntity<PaymentResponseDto> getPaymentDetailsByOrderId(@PathVariable Long orderId){

        PaymentResponseDto paymentResponseDto=paymentService.getPaymentDetailsByOrderId(orderId);

        return new ResponseEntity<>(paymentResponseDto,HttpStatus.OK);

    }

}
