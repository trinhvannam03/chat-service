package com.project.chatservice.services;

import com.project.chatservice.dto.ConversationDTO;
import com.project.chatservice.dto.ConversationParticipantDTO;
import com.project.chatservice.dto.UserEntityCDC;
import com.project.chatservice.entities.Conversation;
import com.project.chatservice.entities.ConversationParticipant;
import com.project.chatservice.enums.ConversationStatus;
import com.project.chatservice.enums.ConversationType;
import com.project.chatservice.repositories.ConversationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConversationService {
    private final ConversationRepository conversationRepository;

    public List<ConversationDTO> findConversations(String userEntityId) {
        List<Conversation> conversations = conversationRepository.findBriefConversations(userEntityId);
        List<ConversationDTO> conversationDTOs = new ArrayList<>();
        for (Conversation conversation : conversations) {
            ConversationDTO conversationDTO = conversation.toDTO();
            List<ConversationParticipantDTO> participantDTOS = new ArrayList<>();
            for (ConversationParticipant conversationParticipant : conversation.getParticipants()) {
                ConversationParticipantDTO conversationParticipantDTO = conversationParticipant.toDTO();
                participantDTOS.add(conversationParticipantDTO);
            }
            conversationDTO.setParticipants(participantDTOS);
            conversationDTOs.add(conversationDTO);
        }
        return conversationDTOs;
    }

    public List<UserEntityCDC> findActiveUsers() {
        List<UserEntityCDC> users = new ArrayList<>();
        return users;
    }

    public ConversationDTO createConversation(ConversationDTO conversationDTO) {
        List<ConversationParticipantDTO> participants = conversationDTO.getParticipants();
        Conversation conversation = Conversation.builder().conversationType((participants.size() == 2) ? ConversationType.PRIVATE : ConversationType.GROUP).conversationStatus(ConversationStatus.ACTIVE).build();
        for (ConversationParticipantDTO participantDTO : participants) {
            ConversationParticipant participant = ConversationParticipant.builder().conversation(conversation).build();
        }
        return conversationDTO;
    }
}
