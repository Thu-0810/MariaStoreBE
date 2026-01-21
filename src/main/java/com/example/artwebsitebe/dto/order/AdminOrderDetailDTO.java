package com.example.artwebsitebe.dto.order;

import com.example.artwebsitebe.enums.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record AdminOrderDetailDTO(
        Long id,
        String orderCode,
        LocalDateTime createdAt,
        BigDecimal totalAmount,
        OrderStatus status,
        String receiverName,
        String receiverPhone,
        String shippingAddress,

        String invoiceCode,
        String paymentMethod,
        String paymentStatus,
        LocalDateTime paidAt,

        List<AdminOrderItemDTO> items
) {}