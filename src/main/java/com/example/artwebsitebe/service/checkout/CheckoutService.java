package com.example.artwebsitebe.service.checkout;

import com.example.artwebsitebe.dto.checkout.CheckoutRequestDTO;
import com.example.artwebsitebe.dto.checkout.OrderSummaryDTO;

public interface CheckoutService {
    OrderSummaryDTO checkoutFromCart(String email, CheckoutRequestDTO req);
    OrderSummaryDTO getOrderSummary(String email, Long orderId);
}