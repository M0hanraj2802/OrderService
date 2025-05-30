package com.ecommerce.orderservice.dto.request;

import com.ecommerce.orderservice.entity.OrderItem;
import com.ecommerce.orderservice.enums.OrderStatus;
import com.ecommerce.orderservice.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private List<OrderItem> orderItems;
    private UUID customerIdentifier;
    private int totalQuantity;
    private long totalAmount;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
}
