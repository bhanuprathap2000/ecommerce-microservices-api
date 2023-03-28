package com.microservices.PaymentService.service;

import com.microservices.PaymentService.model.PaymentRequestDto;
import com.microservices.PaymentService.model.PaymentResponseDto;

public interface PaymentService {

    Long doPayment(PaymentRequestDto paymentRequestDto);

    PaymentResponseDto getPaymentDetailsByOrderId(Long orderId);
}
