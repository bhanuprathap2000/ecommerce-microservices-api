package com.microservices.PaymentService.service;

import com.microservices.PaymentService.entity.PaymentDetails;
import com.microservices.PaymentService.model.PaymentMode;
import com.microservices.PaymentService.model.PaymentRequestDto;
import com.microservices.PaymentService.model.PaymentResponseDto;
import com.microservices.PaymentService.model.PaymentStatus;
import com.microservices.PaymentService.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;


    PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    @Override
    public Long doPayment(PaymentRequestDto paymentRequestDto) {

        PaymentDetails paymentDetails = PaymentDetails.builder().paymentDate(Instant.now()).paymentStatus(PaymentStatus.SUCCESS.toString()).paymentMode(PaymentMode.UPI.toString()).amount(paymentRequestDto.getAmount()).orderId(paymentRequestDto.getOrderId()).referenceNumber(paymentRequestDto.getReferenceNumber()).build();
        paymentRepository.save(paymentDetails);
        return paymentDetails.getId();
    }

    @Override
    public PaymentResponseDto getPaymentDetailsByOrderId(Long orderId) {

        PaymentDetails paymentDetails= paymentRepository.findByOrderId(orderId);

        return PaymentResponseDto.builder()
                .price(paymentDetails.getAmount())
                .productId(paymentDetails.getId())
                .paymentStatus(paymentDetails.getPaymentStatus())
                .build();

    }
}
