package com.project.chatservice.entities;

import com.project.chatservice.dto.ConversationParticipantDTO;
import com.project.chatservice.enums.ParticipantStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConversationParticipant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long conversationParticipantId;

    @Column(name = "conversation_id")
    private long conversationId;

    @Column(name = "participant_id")
    private String participantId;

    @Enumerated(EnumType.STRING)
    @Column(name = "participant_status")
    private ParticipantStatus participantStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_id", insertable = false, updatable = false)
    private UserEntity participant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id", insertable = false, updatable = false)
    private Conversation conversation;

    public ConversationParticipantDTO toDTO() {
        return ConversationParticipantDTO
                .builder()
                .conversationId(conversationId)
                .participant(participant.toDTO())
                .build();
    }
}
