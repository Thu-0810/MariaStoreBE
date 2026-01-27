package com.example.artwebsitebe.dto.chat;

public record WsMarkReadRequestDTO(
        Long conversationId,
        Long lastReadMessageId
) {}