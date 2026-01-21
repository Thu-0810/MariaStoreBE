package com.example.artwebsitebe.dto.user;

import com.example.artwebsitebe.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AdminUserOrderRowDTO(
        Long orderId,
        String orderCode,
        LocalDateTime createdAt,
        BigDecimal totalAmount,
        OrderStatus status
) {}