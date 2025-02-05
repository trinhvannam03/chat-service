package com.project.chatservice.repositories;

import com.project.chatservice.entities.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    @Query("SELECT DISTINCT c FROM Conversation c " +
            "JOIN FETCH c.participants pars " +
            "JOIN FETCH pars.participant par " +
            "       WHERE EXISTS (" +
            "                   SELECT 1 FROM ConversationParticipant p " +
            "                   WHERE p.conversationId = c.conversationId " +
            "                   AND p.participantId = :userEntityId) " +
            "           AND pars.participantId != :userEntityId")
    List<Conversation> findBriefConversations(String userEntityId);
}
