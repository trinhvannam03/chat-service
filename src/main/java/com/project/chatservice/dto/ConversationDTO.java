package com.project.chatservice.dto;

import com.project.chatservice.enums.ConversationStatus;
import com.project.chatservice.enums.ConversationType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ConversationDTO {
    private Long conversationId;
    private ConversationStatus conversationStatus;
    private ConversationType conversationType;
    private List<ConversationParticipantDTO> participants = new ArrayList<>();
    private List<MessageDTO> messages = new ArrayList<>();

}
