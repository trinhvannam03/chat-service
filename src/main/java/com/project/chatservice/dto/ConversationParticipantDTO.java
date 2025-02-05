package com.project.chatservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.chatservice.enums.ParticipantStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
public class ConversationParticipantDTO {
    private Long participantId;
    private Long conversationId;
    private ParticipantStatus participantStatus;
    private UserEntityDTO participant;
}
