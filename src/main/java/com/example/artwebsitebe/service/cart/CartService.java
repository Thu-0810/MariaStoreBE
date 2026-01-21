package com.example.artwebsitebe.service.cart;

import com.example.artwebsitebe.dto.cart.CartDTO;

public interface CartService {
    CartDTO getMyCart(String email);
    CartDTO addToCart(String email, Long productId, Integer quantity);
    CartDTO updateQuantity(String email, Long productId, Integer quantity);
    void removeItem(String email, Long productId);
    void clearCart(String email);
}