package com.example.artwebsitebe.dto.order;

import com.example.artwebsitebe.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record MyOrderDTO(
        Long id,
        String orderCode,
        LocalDateTime createdAt,
        BigDecimal totalAmount,
        OrderStatus status,

        String paymentMethod,
        String paymentStatus,
        LocalDateTime paidAt,

        List<MyOrderItemDTO> items
) {}