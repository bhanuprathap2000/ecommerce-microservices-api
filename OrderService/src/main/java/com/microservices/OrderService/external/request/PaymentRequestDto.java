package com.microservices.OrderService.external.request;

import com.microservices.OrderService.model.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequestDto {
    private Long orderId;
    private Long amount;
    private String referenceNumber;
    private PaymentMode paymentMode;

}
