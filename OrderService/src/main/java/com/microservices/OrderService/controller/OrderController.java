package com.microservices.OrderService.controller;

import com.microservices.OrderService.model.OrderRequestDto;
import com.microservices.OrderService.model.OrderResponseDto;
import com.microservices.OrderService.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    OrderController(OrderService orderService){
        this.orderService=orderService;
    }

    @PostMapping("/create")
    ResponseEntity<Long> createOrder(@RequestBody OrderRequestDto orderRequestDto){


        Long orderId= orderService.createOrder(orderRequestDto);

        return new ResponseEntity<>(orderId, HttpStatus.CREATED);

    }

    @GetMapping("/{orderId}")
    ResponseEntity<OrderResponseDto> fetchOrderDetails(@PathVariable Long orderId){

        OrderResponseDto orderResponseDto= orderService.fetchOrderDetails(orderId);

        return new ResponseEntity<>(orderResponseDto, HttpStatus.OK);

    }
}
