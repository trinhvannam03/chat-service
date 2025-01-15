package com.project.chatservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.chatservice.dto.MessageDTO;
import com.project.chatservice.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @MessageMapping("/message")
    public void sendMessage(@Payload MessageDTO messageDTO) throws JsonProcessingException {
        System.out.println(messageDTO);
        messageService.emitMessage(messageDTO);
    }
}
