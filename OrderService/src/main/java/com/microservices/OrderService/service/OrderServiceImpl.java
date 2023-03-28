package com.microservices.OrderService.service;

import com.microservices.OrderService.entity.Order;
import com.microservices.OrderService.exception.OrderException;
import com.microservices.OrderService.external.client.PaymentService;
import com.microservices.OrderService.external.client.ProductService;
import com.microservices.OrderService.external.request.PaymentRequestDto;
import com.microservices.OrderService.model.*;
import com.microservices.OrderService.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final ProductService  productService;

    private final RestTemplate restTemplate;

    private PaymentService paymentService;

    OrderServiceImpl(OrderRepository orderRepository,ProductService productService,RestTemplate restTemplate){
        this.orderRepository=orderRepository;
        this.productService=productService;
        this.restTemplate=restTemplate;
    }

    @Override
    public Long createOrder(OrderRequestDto orderRequestDto) {

        //create a order entity

        productService.reduceProductQuantity(orderRequestDto.getProductId(),orderRequestDto.getQuantity());

        Order order = Order.builder().productId(orderRequestDto.getProductId()).amount(orderRequestDto.getTotalAmount()).orderStatus(orderRequestDto.getPaymentMode().name()).orderDate(Instant.now()).amount(orderRequestDto.getTotalAmount()).quantity(orderRequestDto.getQuantity()).build();

        orderRepository.save(order);

        PaymentRequestDto paymentRequestDto = PaymentRequestDto.builder()
                        .orderId(order.getOrderId())
                                .paymentMode(PaymentMode.UPI)
                                        .amount(order.getAmount())
                                                .referenceNumber(UUID.randomUUID().toString()).build();
        String orderStatus=null;

        try{
            paymentService.doPayment(paymentRequestDto);
            orderStatus="PLACED";
        }
        catch(Exception ex){
            orderStatus="PAYMENT_FAILED";
        }

        order.setOrderStatus(orderStatus);
        orderRepository.save(order);

        return order.getOrderId();
    }

    @Override
    public OrderResponseDto fetchOrderDetails(Long orderId) {

        Order order= orderRepository.findById(orderId).orElseThrow(()-> new OrderException("Order With Given Id Not Found","404"));

        ProductDetails productDetails = restTemplate.getForObject("http://PRODUCT-SERVICE/product/"+orderId,ProductDetails.class);


        PaymentResponseDto paymentResponseDto = restTemplate.getForObject("http://PAYMENT-SERVICE/product/"+orderId,PaymentResponseDto.class);

        return OrderResponseDto.builder()
                .amount(order.getAmount())
                .orderDate(order.getOrderDate())
                .orderId(order.getOrderId())
                .orderStatus(order.getOrderStatus())
                .productDetails(productDetails)
                .paymentDetails(paymentResponseDto)
                .build();
    }

}
