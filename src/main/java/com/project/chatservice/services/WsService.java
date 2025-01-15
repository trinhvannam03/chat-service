package com.project.chatservice.services;

import com.project.chatservice.dto.WsConnection;
import com.project.chatservice.aws.WsPrincipal;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Service
@RequiredArgsConstructor
public class WsService {
//    private final SimpMessagingTemplate messagingTemplate;
//    private final SimpUserRegistry simpUserRegistry;
    private final RedisTemplate<String, Object> redisTemplate;


    @EventListener
    public void handleSubscriptionEvent(SessionSubscribeEvent event) {
        StompHeaderAccessor headers = StompHeaderAccessor.wrap(event.getMessage());
        System.out.println("User subscribed to: " + headers.getDestination());
        System.out.println("Session ID: " + headers.getSessionId());
    }

    @EventListener
    //listen to event and save connection to redis
    public void handleConnectionEvent(SessionConnectedEvent event) {
        StompHeaderAccessor headers = StompHeaderAccessor.wrap(event.getMessage());
        System.out.println("connection: " + headers.getUser());
        WsPrincipal principal = (WsPrincipal) event.getUser();
        assert principal != null;
        WsConnection wsConnection = WsConnection
                .builder()
                .email(principal.getEmail())
                .username(principal.getUsername())
                .build();
        redisTemplate.opsForValue().set(principal.getUserId(), wsConnection);
    }

    public List<WsConnection> getConnections(Set<String> keys) {
        List<Object> objects = redisTemplate.opsForValue().multiGet(keys);
        List<WsConnection> connections = new ArrayList<>();
        assert objects != null;
        for (Object object : objects) {
            WsConnection wsConnection = (WsConnection) object;
            connections.add(wsConnection);
        }
        return connections;
    }

    public void logSubscriptions() {
//        Set<SimpUser> users = simpUserRegistry.getUsers();
//        users.forEach(user -> {
//            WsPrincipal wsPrincipal = (WsPrincipal) user.getPrincipal();
//            assert wsPrincipal != null;
//            System.out.println(wsPrincipal.getProfilePicture());
//        });
    }

}
