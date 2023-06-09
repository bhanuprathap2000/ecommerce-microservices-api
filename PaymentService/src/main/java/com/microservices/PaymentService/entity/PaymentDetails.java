package com.microservices.PaymentService.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="order_id")
    private Long orderId;
    @Column(name="payment_mode")
    private String paymentMode;
    @Column(name="reference_number")
    private String referenceNumber;
    @Column(name="payment_date")
    private Instant paymentDate;
    @Column(name="payment_status")
    private String paymentStatus;
    @Column(name="amount")
    private Long amount;
}
