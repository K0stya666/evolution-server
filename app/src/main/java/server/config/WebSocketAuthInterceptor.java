package server.config;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import server.utils.JwtUtil;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class WebSocketAuthInterceptor implements HandshakeInterceptor {
    private final JwtUtil jwtUtil;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response,
                                   WebSocketHandler wsHandler,
                                   Map<String, Object> attributes
    ) throws Exception {
        List<String> authHeaders = request.getHeaders().get("Authorization");
        if (authHeaders != null && !authHeaders.isEmpty()) {
            String token = authHeaders.get(0).replace("Bearer ", "");
            Claims claims = jwtUtil.resolveClaims(token);
            if (jwtUtil.isTokenValid(claims)) {
                // Put user info into handshake attributes
                attributes.put("userId", claims.get("id"));
                return true; // proceed
            }
        }
        response.setStatusCode(HttpStatus.FORBIDDEN);
        return false; // Reject handshake
    }
    @Override
    public void afterHandshake(ServerHttpRequest request,
                               ServerHttpResponse response,
                               WebSocketHandler wsHandler,
                               Exception exception
    ) {

    }

    private String extractToken(ServerHttpRequest request) {

        return request.getHeaders().getFirst("Authorization");
    }
}
