package server.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private HttpHandshakeInterceptor httpHandshakeInterceptor;
//    private final StompChannelInterceptor stompChannelInterceptor;

//    @Override
//    public void configureClientInboundChannel(ChannelRegistration registration) {
//        registration.interceptors(stompChannelInterceptor);
//    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
            .addEndpoint("/ws") // URL WebSocket-соединения
            .setAllowedOriginPatterns("*") // Разрешаем подключения со всех доменов
            .addInterceptors(httpHandshakeInterceptor);
//            .withSockJS(); // Поддержка SockJS (для совместимости со старыми браузерами)
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic", "/queue"); // Простой брокер сообщений
        registry.setApplicationDestinationPrefixes("/app"); // Префикс для обработки сообщений
    }
}
