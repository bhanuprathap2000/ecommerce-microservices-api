package com.microservices.OrderService.service;


import com.microservices.OrderService.model.OrderRequestDto;
import com.microservices.OrderService.model.OrderResponseDto;

public interface OrderService  {

    Long createOrder(OrderRequestDto orderRequestDto);

    OrderResponseDto fetchOrderDetails(Long orderId);
}
