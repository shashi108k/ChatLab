package org.lab.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker  // it enables WebSocket message handling, backed by a message broker.
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");  // First, we enable an in-memory message broker to carry the messages back to the client on destinations prefixed with �/topic�.
        config.setApplicationDestinationPrefixes("/app");  // We complete our simple configuration by designating the �/app� prefix to filter destinations targeting application annotated methods (via @MessageMapping).
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/livescore-websocket")
        		.setAllowedOrigins("*")
        		.withSockJS();
        
        registry.addEndpoint("/chat").setAllowedOrigins("*").withSockJS();
    }

}