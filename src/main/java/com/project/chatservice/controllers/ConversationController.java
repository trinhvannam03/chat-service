package com.project.chatservice.controllers;

import com.project.chatservice.dto.ConversationDTO;
import com.project.chatservice.entities.Conversation;
import com.project.chatservice.services.ConversationService;
import com.project.chatservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/conversation")
@RequiredArgsConstructor
public class ConversationController {
    private final ConversationService conversationService;
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/")
    ResponseEntity<ConversationDTO> createConversation(@AuthenticationPrincipal Jwt jwt) {

        return ResponseEntity.ok(null);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/")
    ResponseEntity<List<ConversationDTO>> getConversations(@AuthenticationPrincipal Jwt jwt) {
        String userEntityId = jwt.getClaims().get("sub").toString();
        try {
            List<ConversationDTO> conversations = conversationService.findConversations(userEntityId);
            return ResponseEntity.ok(conversations);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
