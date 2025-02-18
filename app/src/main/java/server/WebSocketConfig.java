package server;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Точки, куда сервер отправляет сообщения клиентам
        config.enableSimpleBroker("/topic");
        // Префикс для маппинга входящих сообщений от клиента
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Endpoint для подключения SockJS/STOMP клиентов
        registry.addEndpoint("/ws")
                .setAllowedOrigins("http://127.0.0.1:5173")
                .withSockJS();
    }
}
