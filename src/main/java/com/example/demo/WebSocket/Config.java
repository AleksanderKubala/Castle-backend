package com.example.demo.WebSocket;

import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

//@Configuration
//@EnableWebSocketMessageBroker
public class Config implements WebSocketMessageBrokerConfigurer {

    /*@Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/hello")
                .setAllowedOrigins("*")
                .withSockJS();
    }*/
}
