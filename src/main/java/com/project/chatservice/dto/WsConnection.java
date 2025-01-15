package com.project.chatservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class WsConnection implements Serializable {
    @Serial
    private static final long serialVersionUID = 12L;

    private String userId;
    private String fullName;
    private String username;
    private String profilePicture;
    private String email;
    private LocalDateTime closedAt;
    private LocalDateTime establishedAt;
    private ConnectionStatus connectionStatus;

    public enum ConnectionStatus {
        CLOSED,
        ACTIVE
    }
}
