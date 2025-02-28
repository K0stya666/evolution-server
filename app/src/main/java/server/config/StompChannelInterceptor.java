package server.config;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;
import server.utils.JwtUtil;

@Component
@RequiredArgsConstructor
public class StompChannelInterceptor implements ChannelInterceptor {
    private final JwtUtil jwtUtil;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            // 1) Check if there's a token
            String token = accessor.getFirstNativeHeader("Authorization");

            // 2) If token is present → validate
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
                // 3) No token? → treat as "anonymous" principal
                accessor.setUser(() -> "anonymous");
            }
        }
        return message;
    }
}

