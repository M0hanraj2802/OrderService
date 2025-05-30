package com.ecommerce.orderservice.service.impl;

import com.ecommerce.orderservice.dto.request.OrderRequest;
import com.ecommerce.orderservice.dto.response.OrderResponse;
import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.entity.OrderItem;
import com.ecommerce.orderservice.exception.ResourceNotFoundException;
import com.ecommerce.orderservice.repository.OrderItemRepository;
import com.ecommerce.orderservice.repository.OrderRepository;
import com.ecommerce.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    private final ModelMapper modelMapper;

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        Order order = modelMapper.map(orderRequest, Order.class);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        return modelMapper.map(orderRepository.save(order),OrderResponse.class);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        List<OrderResponse> orderResponses=new ArrayList<>();
        for(Order order:orderRepository.findAll())
            orderResponses.add(modelMapper.map(order,OrderResponse.class));
        return orderResponses;
    }

    @Override
    public OrderResponse getOrder(UUID orderId) {
        Order order=orderRepository.findById(orderId).orElseThrow(()->new ResourceNotFoundException("Resource not found"));
        return modelMapper.map(order,OrderResponse.class);
    }

    @Override
    public OrderResponse updateOrder(UUID orderId, OrderRequest orderRequest) {
        Order order=orderRepository.findById(orderId).orElseThrow(()->new ResourceNotFoundException("Resource not found"));

        order.setOrderItems(orderRequest.getOrderItems());
        order.setTotalQuantity(orderRequest.getTotalQuantity());
        order.setTotalAmount(orderRequest.getTotalAmount());
        order.setOrderStatus(orderRequest.getOrderStatus());
        order.setPaymentStatus(orderRequest.getPaymentStatus());

        order.setUpdatedAt(LocalDateTime.now());
        return modelMapper.map(orderRepository.save(order),OrderResponse.class);
    }

    @Override
    public String deleteOrder(UUID orderId) {
        orderRepository.deleteById(orderId);
        return "Order Deleted Successfully";
    }
}
