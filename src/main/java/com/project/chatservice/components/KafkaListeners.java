package com.project.chatservice.components;

import com.project.chatservice.services.MessageService;
import com.project.chatservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaListeners {
    private final UserService userService;
    private final MessageService messageService;

    /*The listener is a higher-level abstraction that provides more automation, such as
    message handling, error handling, and automatic acknowledgment of consumed messages.*/
    @KafkaListener(
            topics = {"keycloak.keycloak.users"},
            containerFactory = "databaseChangeListenerContainerFactory"
    )
    public void captureUserChange(String message) {
        try {
            String currentThreadName = Thread.currentThread().getName();
            userService.syncUserEntity(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(
            topics = {"ws_messages"},
            containerFactory = "wsMessageListenerContainerFactory"
    )
    public void listenAndForward(String message) {
        String currentThreadName = Thread.currentThread().getName();
        try {
            messageService.resolveAndForward(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
