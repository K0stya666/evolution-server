package server.config;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;
import server.utils.JwtUtil;

@Slf4j
@Component
@RequiredArgsConstructor
public class StompChannelInterceptor implements ChannelInterceptor {
    private final JwtUtil jwtUtil;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            // 1) Check if there's a token in headers (WebSocket case)
            String token = accessor.getFirstNativeHeader("Authorization");
            log.info("log token: {}", token);

            // TODO где-то здесь косяк.
            //  Я могу передеть токен пользователю, но не могу от пользователя принять токен.
            //  Потенциально два варианта пофиксить:
            //  - Разобраться, почему это не работает в нынешней реализации (где мы используем stompChannelInterceptor и SockJS) и сделать так, чтобы работало
            //  - Перейти на реализацию с читыми веб сокетами, без SockJS b c HttpHandshakeInterceptor'ом (там закоменчена реализация для чистых веб сокетов). Но тогда и на фронте реализаця немного поменятеся

            if (token == null) {
                // 2) Extract token from SockJS "Sec-WebSocket-Protocol" header
                String query = accessor.getFirstNativeHeader("Sec-WebSocket-Protocol");
                log.info("log query: {}", query);
                token = extractTokenFromQuery(query);

//                // 2) Fall back to handshake attribute: "myToken"
//                Object rawToken = accessor.getSessionAttributes().get("myToken");
//                if (rawToken != null) {
//                    token = "Bearer " + rawToken; // if your server expects 'Bearer ' prefix
//                }
            }


            // 3) Validate token
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring("Bearer ".length());
                Claims claims = jwtUtil.resolveClaims(token);
                if (jwtUtil.isTokenValid(claims)) {
                    // Attach user ID as principal’s name
                    accessor.setUser(() -> claims.get("id").toString());
                } else {
                    throw new IllegalArgumentException("Invalid Token");
                }
            } else {
                // 4) No token? → treat as "anonymous" principal
                accessor.setUser(() -> "anonymous");
            }
        }
        return message;
    }

    // Helper function to extract token from query string
    private String extractTokenFromQuery(String query) {
        log.info("Enter extractToken... {}", query);
        if (query != null && query.contains("token=")) {
            log.info("Found token in query {}...", query);
            String extractedToken = query.split("token=")[1].split("&")[0]; // Extracts token from query
            log.info("Extracted token: {}", extractedToken);
            return extractedToken;
        }
        return null;
    }
}

