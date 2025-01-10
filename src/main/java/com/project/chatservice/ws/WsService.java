package com.project.chatservice.ws;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Data

public class WsService {
    @Cacheable(value = "ws_connections", key = "#userId")
    public Set<Void> getSessions(Long userId) {
        return new HashSet<>();
    }
}
