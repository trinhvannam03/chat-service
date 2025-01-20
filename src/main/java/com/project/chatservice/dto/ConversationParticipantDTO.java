package com.project.chatservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParticipantDTO {
    private Long participantId;
    private Long conversationId;
    private Long userId;
}
