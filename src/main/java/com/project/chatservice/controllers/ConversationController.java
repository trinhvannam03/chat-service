package com.project.chatservice.controllers;

import com.project.chatservice.dto.ConversationDTO;
import com.project.chatservice.entities.Conversation;
import com.project.chatservice.enums.ConversationStatus;
import com.project.chatservice.enums.ConversationType;
import com.project.chatservice.services.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/conversation")
@RequiredArgsConstructor
public class ConversationController {
    private final ConversationService conversationService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/")
    ResponseEntity<ConversationDTO> createConversation(
            @AuthenticationPrincipal Jwt jwt) {
        System.out.println(jwt);
        return ResponseEntity.ok(null);
    }
}
