package com.example.demo;

import com.example.demo.Properties.BattleProperties;
import com.example.demo.Properties.SecurityProperties;
import com.example.demo.Properties.StorageUpdateProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@SpringBootApplication
@EnableWebSocketMessageBroker
@EnableWebSocket
@EnableConfigurationProperties
public class DemoApplication implements WebSocketMessageBrokerConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	/*
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:4200", "http://localhost:8100")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "UPGRADE")
						.allowCredentials(true);
			}
		};
	}
*/
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/socket").withSockJS().setWebSocketEnabled(true);

	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("http://localhost:4200")
				.enableSimpleBroker("/update");
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityProperties securityProperties() {
		return new SecurityProperties();
	}

	@Bean
	public StorageUpdateProperties updateProperties() {
		return new StorageUpdateProperties();
	}

	@Bean
	public BattleProperties battleProperties() {
		return new BattleProperties();
	}

}
