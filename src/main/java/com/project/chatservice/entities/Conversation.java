package com.project.chatservice.entities;

import com.project.chatservice.dto.ConversationDTO;
import com.project.chatservice.enums.ConversationStatus;
import com.project.chatservice.enums.ConversationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "conversation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long conversationId;

    @Enumerated(EnumType.STRING)
    @Column(name = "conversation_type")
    private ConversationType conversationType;
    @Enumerated(EnumType.STRING)
    @Column(name = "conversation_status")
    private ConversationStatus conversationStatus;


    private String conversationName;

    @OneToMany(mappedBy = "conversation")
    private List<ConversationParticipant> participants;

    @OneToMany(mappedBy = "conversation")
    private List<Message> messages;


    public ConversationDTO toDTO() {
        return ConversationDTO
                .builder()
                .conversationId(conversationId)
                .conversationType(conversationType)
                .conversationStatus(conversationStatus)
                .build();
    }
}


