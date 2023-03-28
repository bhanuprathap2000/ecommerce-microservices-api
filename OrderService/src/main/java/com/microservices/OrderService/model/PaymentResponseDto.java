package com.microservices.OrderService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDto {
    private Long productId;
    private Long price;
    private String productName;
    private Long quantity;
    private String paymentStatus;
}
