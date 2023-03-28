package com.microservices.OrderService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name= "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @Column(name= "product_id")
    private Long productId;
    @Column(name="quantity")
    private Long quantity;
    @Column(name="orderDate")
    private Instant orderDate;
    @Column(name="order_status")
    private String orderStatus;
    @Column(name="amount")
    private Long amount;
}
