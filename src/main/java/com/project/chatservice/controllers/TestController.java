package com.project.chatservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.chatservice.dto.MessageDTO;
import com.project.chatservice.dto.WsConnection;
import com.project.chatservice.services.MessageService;
import com.project.chatservice.services.WsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final MessageService messageService;
    private final WsService wsService;
    private final RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/")
    public ResponseEntity<String> getAllMessages() {
        return ResponseEntity.ok("HELLO");
    }

    @GetMapping("/{id}")
    public ResponseEntity<WsConnection> test(@PathVariable Long id) throws JsonProcessingException {

        WsConnection wsConnection = WsConnection
                .builder()
                .email("wsPrincipal@gmail.com")
                .establishedAt(LocalDateTime.now())
                .userId(String.valueOf(id))
                .username("trinhvannam")
                .build();
        redisTemplate.opsForValue().set("ws_connections::" + id, wsConnection);
        return ResponseEntity.ok(wsConnection);
    }

    @GetMapping("/{id}/get")
    public ResponseEntity<List<Object>> get(@PathVariable String id) {
        Object obj = redisTemplate.opsForValue().get("ws_connections::" + id);
//        assert keys != null;
//        System.out.println("There are" + keys.size() + " keys");
//        List<Object> connections = redisTemplate.opsForValue().multiGet(keys);
        return ResponseEntity.ok(null);
    }
}

