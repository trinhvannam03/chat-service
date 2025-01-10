package com.project.chatservice.dto;

import com.project.chatservice.ws.WsPrincipal;
import lombok.Data;

@Data
public class WsConnection {
    private WsPrincipal principal;
}
