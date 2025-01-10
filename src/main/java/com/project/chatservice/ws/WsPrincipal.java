package com.project.chatservice.ws;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Principal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WsPrincipal implements Principal {
    private String userId;
    private String fullName;
    private String username;
    private String profilePicture;
    private String email;

    @Override
    public String getName() {
        return userId;
    }
}
