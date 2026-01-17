package com.example.artwebsitebe.dto.cart;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateCartItemRequestDTO {
    @NotNull
    private Integer quantity;
}