package com.example.artwebsitebe.dto.order;

import com.example.artwebsitebe.enums.OrderStatus;

public record AdminOrderUpdateRequestDTO(
        OrderStatus status,
        String receiverName,
        String receiverPhone,
        String shippingAddress
) {}