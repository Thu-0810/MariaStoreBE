package com.example.artwebsitebe.dto.chat;

public record WsSendMessageRequestDTO(
        Long conversationId,
        String contentType,
        String content,
        String metadata,
        Long replyToMessageId
) {}