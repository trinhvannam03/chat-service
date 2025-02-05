package com.project.chatservice.controllers;

import com.project.chatservice.dto.UserEntityDTO;
import com.project.chatservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    ResponseEntity<List<UserEntityDTO>> getConversations() {
        List<UserEntityDTO> userDTOS = userService.getUsers();
        return ResponseEntity.ok(userDTOS);
    }
}
