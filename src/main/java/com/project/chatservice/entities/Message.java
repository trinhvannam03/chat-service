package com.project.chatservice.entities;

import com.project.chatservice.dto.MessageDTO;
import com.project.chatservice.enums.ContentType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.sql.Timestamp;

@Entity
@Table(name = "message")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long messageId;

    @Column(name = "conversation_id", nullable = false)
    private Long conversationId;

    @Column(name = "sender_id", nullable = false)
    private String senderId;


    @Column(name = "content", length = 100000)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "content_type", nullable = false)
    private ContentType contentType;

    @Column(name = "time_stamp", nullable = false)
    private Timestamp timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", insertable = false, updatable = false)
    private UserEntity sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id", insertable = false, updatable = false)
    private Conversation conversation;



    public MessageDTO toDTO() {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setContent(this.content);
        messageDTO.setContentType(this.contentType);
        messageDTO.setTimestamp(this.toDTO().getTimestamp());
        return messageDTO;
    }
}
