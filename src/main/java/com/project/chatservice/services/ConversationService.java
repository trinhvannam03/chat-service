package com.project.chatservice.services;

import com.project.chatservice.dto.ConversationDTO;
import com.project.chatservice.dto.ParticipantDTO;
import com.project.chatservice.dto.UserDTO;
import com.project.chatservice.entities.Conversation;
import com.project.chatservice.entities.Participant;
import com.project.chatservice.enums.ConversationStatus;
import com.project.chatservice.enums.ConversationType;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConversationService {
    public List<ConversationDTO> findAllConversations(Long userId) {
        List<ConversationDTO> conversations = new ArrayList<>();
        return conversations;
    }

    public List<UserDTO> findActiveUsers() {
        List<UserDTO> users = new ArrayList<>();
        return users;
    }

    public ConversationDTO createConversation(ConversationDTO conversationDTO) {
        List<ParticipantDTO> participants = conversationDTO.getParticipants();
        Conversation conversation = Conversation.builder()
                .conversationType((participants.size() == 2) ? ConversationType.PRIVATE : ConversationType.GROUP)
                .conversationStatus(ConversationStatus.ACTIVE)
                .build();
        for (ParticipantDTO participantDTO : participants) {
            Participant participant = Participant.builder()
                    .conversation(conversation)
                    .build();
        }
        return conversationDTO;
    }
}
