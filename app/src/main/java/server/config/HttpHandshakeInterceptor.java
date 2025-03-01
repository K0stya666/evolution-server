package server.config;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import server.utils.JwtUtil;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class HttpHandshakeInterceptor implements HandshakeInterceptor {
    private final JwtUtil jwtUtil;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response,
                                   WebSocketHandler wsHandler,
                                   Map<String, Object> attributes
    ) {
        String token = null;
        List<String> authHeaders = request.getHeaders().get("Authorization");
//        if (authHeaders != null && !authHeaders.isEmpty()) {
//            token = authHeaders.get(0).replace("Bearer ", "");
////            String token = authHeaders.get(0).replace("Bearer ", "");
////            Claims claims = jwtUtil.resolveClaims(token);
////            if (jwtUtil.isTokenValid(claims)) {
////                // Put user info into handshake attributes
////                attributes.put("userId", claims.get("id"));
////                return true; // proceed
////            }
//        }
        if (request instanceof ServletServerHttpRequest servletRequest) {
            HttpServletRequest httpServletRequest = servletRequest.getServletRequest();
            token = httpServletRequest.getParameter("token");
        }

        if (token != null) {
            Claims claims = jwtUtil.resolveClaims(token);
            if (jwtUtil.isTokenValid(claims)) {
                attributes.put("userId", claims.get("id"));
                return true;
            }
        }

        response.setStatusCode(HttpStatus.FORBIDDEN);
        return false; // Reject handshake

//        if (request instanceof ServletServerHttpRequest servletRequest) {
//            HttpServletRequest httpServletRequest = servletRequest.getServletRequest();
//            String token = httpServletRequest.getParameter("token");
//            if (token != null) {
//                attributes.put("myToken", token); // store token in attributes
//            }
//        }
//        return true; // proceed
    }
    @Override
    public void afterHandshake(ServerHttpRequest request,
                               ServerHttpResponse response,
                               WebSocketHandler wsHandler,
                               Exception exception
    ) {

    }

//    private String extractToken(ServerHttpRequest request) {
//
//        return request.getHeaders().getFirst("Authorization");
//    }
}
