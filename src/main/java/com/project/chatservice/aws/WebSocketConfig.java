package com.project.chatservice.aws;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.lang.NonNull;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.net.URI;
import java.security.Principal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic", "/queue");
        config.setUserDestinationPrefix("/user");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOriginPatterns("http://localhost:3000", "http://localhost:3001").setHandshakeHandler(new DefaultHandshakeHandler() {
            @Override
            protected Principal determineUser(@NonNull ServerHttpRequest request, @NonNull WebSocketHandler wsHandler, @NonNull Map<String, Object> attributes) {
                URI uri = request.getURI();
                String query = uri.getQuery();
                Map<String, String> params = new HashMap<>();
                if (query == null || query.isEmpty()) {
                    throw new BadCredentialsException("Invalid query");
                }
                String[] pairs = query.split("&");
                for (String pair : pairs) {
                    String[] keyValue = pair.split("=");
                    if (keyValue.length == 2 && keyValue[0].equals("token")) {
                        String token = keyValue[1];
                        try {
                            JWT jwt = JWTParser.parse(token);
                            JWTClaimsSet claimsSet = jwt.getJWTClaimsSet();
                            String userId = claimsSet.getSubject();
                            return WsPrincipal
                                    .builder()
                                    .userId(userId)
                                    .build();
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                throw new RuntimeException();
            }
        }).withSockJS();
    }

}
