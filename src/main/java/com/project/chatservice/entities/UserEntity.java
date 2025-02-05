package com.project.chatservice.entities;

import com.project.chatservice.dto.UserEntityDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_entity")
public class UserEntity {
    @Id
    private String userEntityId;
    private String username;
    private String email;
    private String profilePicture;
    private String phone;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "participant")
    List<ConversationParticipant> participants = new ArrayList<>();


    @OneToMany(mappedBy = "sender")
    private List<Message> sentMessages = new ArrayList<>();

    public UserEntityDTO toDTO() {
        return UserEntityDTO.builder()
                .userEntityId(userEntityId)
                .username(username)
                .email(email)
                .profilePicture(profilePicture)
                .phone(phone)
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }
}
