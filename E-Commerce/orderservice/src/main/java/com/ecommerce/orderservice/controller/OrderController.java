package com.ecommerce.orderservice.controller;

import com.ecommerce.orderservice.dto.request.OrderRequest;
import com.ecommerce.orderservice.dto.response.OrderResponse;
import com.ecommerce.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest)
    {
        OrderResponse orderResponse=orderService.createOrder(orderRequest);
        return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders()
    {
        List<OrderResponse> orderResponses=orderService.getAllOrders();
        return new ResponseEntity<>(orderResponses,HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable UUID orderId)
    {
        OrderResponse orderResponse=orderService.getOrder(orderId);
        return new ResponseEntity<>(orderResponse,HttpStatus.OK);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable UUID orderId, @RequestBody OrderRequest orderRequest)
    {
        OrderResponse orderResponse=orderService.updateOrder(orderId,orderRequest);
        return new ResponseEntity<>(orderResponse,HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteByOrder(@PathVariable UUID orderId)
    {
        String msg=orderService.deleteOrder(orderId);
        return new ResponseEntity<>(msg,HttpStatus.OK);
    }

}
