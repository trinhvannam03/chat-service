package com.project.chatservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.chatservice.aws.WsPrincipal;
import com.project.chatservice.dto.MessageDTO;
import com.project.chatservice.entities.Message;
import com.project.chatservice.repositories.MessageRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Service;

import java.util.Set;

/*
 * The two users having the same subscription path (/user/queue/messages) is
 * EXPECTED BEHAVIOR in a Spring WebSocket application when using user-specific destinations (/user).
 * This is because the @EnableWebSocketMessageBroker configuration ensures that each user gets
 * a unique session and their messages are delivered to the correct user-specific queue.
 * Active WebSocket Subscriptions:
    User: 11217567
    Session: 45221gnd
    Subscription: /user/queue/messages
    *
    User: 11214150
    Session: k0okauag
    Subscription: /user/queue/messages
 */
@Service
@RequiredArgsConstructor
@Data
public class MessageService {
    private final MessageRepository messageRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final SimpUserRegistry simpUserRegistry;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void resolveAndForward(String stringMessage) {
        Message message = new Message();
//        messagingTemplate.convertAndSendToUser(message.getReceiverId().toString(), "/queue/messages", message);
    }


    public void processMessage(MessageDTO messageDTO) throws JsonProcessingException {
        String message = objectMapper.writeValueAsString(messageDTO);
        Set<SimpUser> users = simpUserRegistry.getUsers();
        users.forEach(user -> {
            WsPrincipal wsPrincipal = (WsPrincipal) user.getPrincipal();
            assert wsPrincipal != null;
            System.out.println(wsPrincipal.getProfilePicture());
        });
        kafkaTemplate.send("ws_messages", message);
    }
}

