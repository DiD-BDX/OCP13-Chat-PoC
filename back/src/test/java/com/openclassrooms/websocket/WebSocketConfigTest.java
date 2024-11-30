package com.openclassrooms.websocket;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.StompWebSocketEndpointRegistration;
import org.springframework.web.socket.config.annotation.SockJsServiceRegistration;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.openclassrooms.ycyw.websocket.WebSocketConfig;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = WebSocketConfig.class)
public class WebSocketConfigTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testWebSocketConfigLoaded() {
        WebSocketMessageBrokerConfigurer configurer = applicationContext.getBean(WebSocketMessageBrokerConfigurer.class);
        assertNotNull(configurer, "WebSocketMessageBrokerConfigurer should be loaded in the application context");
    }

    @Test
    public void testConfigureMessageBroker() {
        WebSocketConfig webSocketConfig = new WebSocketConfig();
        MessageBrokerRegistry registry = Mockito.mock(MessageBrokerRegistry.class);
        webSocketConfig.configureMessageBroker(registry);
        // Add assertions to verify the configuration if needed
    }

    @Test
    public void testRegisterStompEndpoints() {
        WebSocketConfig webSocketConfig = new WebSocketConfig();
        StompEndpointRegistry registry = Mockito.mock(StompEndpointRegistry.class);
        StompWebSocketEndpointRegistration registration = Mockito.mock(StompWebSocketEndpointRegistration.class);

        when(registry.addEndpoint(any(String.class))).thenReturn(registration);
        SockJsServiceRegistration sockJsServiceRegistration = Mockito.mock(SockJsServiceRegistration.class);
        when(registration.withSockJS()).thenReturn(sockJsServiceRegistration);

        webSocketConfig.registerStompEndpoints(registry);

        // Add assertions to verify the configuration if needed
    }
}
