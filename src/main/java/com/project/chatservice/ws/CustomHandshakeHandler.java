package com.project.chatservice.ws;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;


public class CustomHandshakeHandler extends DefaultHandshakeHandler {
    @Override
    protected Principal determineUser(
            @NonNull ServerHttpRequest request,
            @NonNull WebSocketHandler wsHandler,
            @NonNull Map<String, Object> attributes) {
        String userId = request.getHeaders().getFirst("X-User-Id");
        String profilePicture = request.getHeaders().getFirst("X-Profile-Picture");
        String email = request.getHeaders().getFirst("X-Email");
        String username = request.getHeaders().getFirst("X-Username");
        return WsPrincipal.builder().userId(userId)
                .email(email)
                .username(username)
                .profilePicture(profilePicture)
                .build();
    }
}