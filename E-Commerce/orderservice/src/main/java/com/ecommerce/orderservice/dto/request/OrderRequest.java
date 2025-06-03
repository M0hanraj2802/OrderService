package com.ecommerce.orderservice.dto.request;

import com.ecommerce.orderservice.entity.OrderItem;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    @NotEmpty(message = "Order must contain at least one item")
    private List<OrderItem> orderItems;

    @NotNull(message = "Customer identifier is required")
    private UUID customerIdentifier;

}
