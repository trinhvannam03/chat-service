package com.project.chatservice.aws;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.security.Principal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WsPrincipal implements Principal, Serializable {
    @Serial
    private static final long serialVersionUID = 10L;

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
