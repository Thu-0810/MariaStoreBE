package com.example.artwebsitebe.dto.checkout;

import java.time.LocalDateTime;

public record PaymentSummaryDTO(
        String paymentMethod,
        String paymentStatus,
        String transactionId,
        LocalDateTime paidAt
) {}