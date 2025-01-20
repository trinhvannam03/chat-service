package com.project.chatservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserDTO {
    private String userEntityId;
    private String username;
    private String email;
    private String profilePicture;
    private String phone;
    private String firstName;
    private String lastName;
}
