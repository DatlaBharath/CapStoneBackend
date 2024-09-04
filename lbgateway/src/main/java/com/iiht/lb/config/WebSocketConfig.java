package com.iiht.lb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Register the WebSocket endpoint that the clients will use to connect to your WebSocket server.
        registry.addEndpoint("/ws/messages")
                .setAllowedOrigins("http://localhost:4200")
                .withSockJS();
    }

    // Other configurations related to message brokers...
}
