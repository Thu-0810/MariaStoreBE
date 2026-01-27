package com.example.artwebsitebe.dto.chat;

import jakarta.validation.constraints.NotNull;

public record MarkReadRequestDTO(@NotNull Long lastReadMessageId) {}