package com.example.artwebsitebe.dto.chat;

public record WsEventDTO(
        String type,
        Object data
) {}