package com.microservices.OrderService.service;

import com.microservices.OrderService.entity.Order;
import com.microservices.OrderService.external.client.PaymentService;
import com.microservices.OrderService.external.client.ProductService;
import com.microservices.OrderService.external.request.PaymentRequestDto;
import com.microservices.OrderService.model.OrderRequestDto;
import com.microservices.OrderService.model.PaymentMode;
import com.microservices.OrderService.repository.OrderRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class OrderServiceImplTest {

    //external dependencies
    @Mock
    private  OrderRepository orderRepository ;

    @Mock
    private  ProductService productService ;

    @Mock
    private RestTemplate restTemplate ;

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private static OrderService orderService; //class under test

    @Test
    public void testCreateOrder() {
        // given
        OrderRequestDto orderRequestDto = OrderRequestDto.builder()
                .productId(1L)
                .quantity(2L)
                .totalAmount(200L)
                .paymentMode(PaymentMode.UPI)
                .build();
        Order order = Order.builder()
                .orderId(1L)
                .productId(1L)
                .quantity(2L)
                .amount(200L)
                .orderStatus(PaymentMode.UPI.name())
                .orderDate(Instant.now())
                .build();
        PaymentRequestDto paymentRequestDto = PaymentRequestDto.builder()
                .orderId(1L)
                .paymentMode(PaymentMode.UPI)
                .amount(200L)
                .referenceNumber(UUID.randomUUID().toString())
                .build();
        doNothing().when(productService).reduceProductQuantity(1L, 2L);
        doReturn(order).when(orderRepository).save(any(Order.class));
        doReturn(null).when(restTemplate).getForObject(anyString(), any(Class.class));
        doReturn(null).when(paymentService).doPayment(any(PaymentRequestDto.class));

        // when
        Long orderId = orderService.createOrder(orderRequestDto);

        // then
        assertEquals(order.getOrderId(), orderId);
    }
}