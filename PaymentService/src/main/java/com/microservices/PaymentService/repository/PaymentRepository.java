package com.microservices.PaymentService.repository;

import com.microservices.PaymentService.entity.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentDetails,Long> {
    PaymentDetails findByOrderId(Long orderId);
}
