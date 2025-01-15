package com.project.chatservice.dto;

import com.project.chatservice.entities.Participant;
import com.project.chatservice.enums.ConversationStatus;
import com.project.chatservice.enums.ConversationType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ConversationDTO {
    private Long conversationId;
    private ConversationStatus conversationStatus;
    private ConversationType conversationType;

    private List<ParticipantDTO> participants = new ArrayList<>();
    private List<MessageDTO> messages = new ArrayList<>();
}
