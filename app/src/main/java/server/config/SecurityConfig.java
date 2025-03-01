package server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // Disable CSRF for testing (enable in production!)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/ws/**").permitAll() // Allow WebSocket access
                        .requestMatchers("/app/auth/**").permitAll() // Allow login & register
                        .anyRequest().authenticated() // Secure other endpoints
                )
                .formLogin().disable() // Disable default login form
                .httpBasic().disable(); // Disable Basic Auth (fixing the issue)

        return http.build();
    }
}
