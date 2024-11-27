package com.openclassrooms.ycyw.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Configure un broker simple pour transmettre les messages
        config.enableSimpleBroker("/topic"); // Destinations pour envoyer les messages (publiques)
        config.setApplicationDestinationPrefixes("/app"); // Préfixe pour les messages provenant du client
    }

    @Override
    public void registerStompEndpoints(org.springframework.web.socket.config.annotation.StompEndpointRegistry registry) {
        // Point d'entrée pour les clients
        registry.addEndpoint("/chat").withSockJS(); // SockJS pour fallback si WebSocket n'est pas supporté
    }
}

