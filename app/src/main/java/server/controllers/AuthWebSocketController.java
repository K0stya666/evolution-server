package server.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import server.network.*;
import server.services.interfaces.UserService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AuthWebSocketController {
    private final UserService userService;

    @MessageMapping("/auth/register")
    @SendToUser("/queue/auth")
    public AuthResponse register(@Payload AuthRequest authRequest) {
        log.info("Пришёл запрос на register");
        return userService.register(authRequest);
    }

    @MessageMapping("/auth/login")
    @SendToUser("/queue/auth")
    public AuthResponse login(@Payload AuthRequest authRequest) {
        log.info("Пришёл запрос на login");
        return userService.login(authRequest);
    }
}
