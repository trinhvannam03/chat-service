package com.project.chatservice.dto;

import com.project.chatservice.enums.ContentType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDTO {
    private String senderId;
    private String content;
    private Long conversationId;
    private LocalDateTime timestamp;
    private ContentType contentType;
}
