package server.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import server.entities.User;

import javax.crypto.SecretKey;
import javax.naming.AuthenticationException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtUtil {

    private static final String SECRET_KEY = "MySuperSecretKeyThatIsLongEnoughToMeetHS256Requirements";
    public static final String TOKEN_HEADER_NAME = "Authorization";
    public static final String PREFIX = "Bearer ";
    private static final long EXPIRATION_MS = 86400000;

    public String createToken(User user) {
        //Claims claims = (Claims) Jwts.claims().setSubject(user.getEmail());

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());

        return Jwts
                .builder()
                .claims(claims)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        String authHeader = request.getHeader(TOKEN_HEADER_NAME);

        if (authHeader != null && authHeader.startsWith(PREFIX)) {
            return authHeader.substring(PREFIX.length());
        }
        return null;
    }

    public String extractData(String token) {
        return (Jwts.parser().setSigningKey(getKey()).build().parseSignedClaims(token).getPayload()).toString();
    }

    public Claims resolveClaims(String token) {
        log.info("в методе resolveClaims");
        return (Jwts.parser().setSigningKey(getKey()).build()).parseClaimsJws(token).getBody();
    }

    public boolean isTokenValid(Claims claims) throws AuthenticationException {
        return claims.getExpiration().after(new Date());
    }

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(SECRET_KEY));
    }
}
