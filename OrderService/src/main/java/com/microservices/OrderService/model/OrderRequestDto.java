package com.microservices.OrderService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequestDto {
    private Long productId;
    private Long quantity;
    private Long totalAmount;
    private PaymentMode paymentMode;
}
