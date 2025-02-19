package server.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import server.network.AuthRequest;
import server.network.AuthResponse;
import server.services.interfaces.UserService;

@CrossOrigin(origins = "http://localhost:5173")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody @Valid AuthRequest authRequest) {
        log.info("Пришёл запрос на register");
        return userService.register(authRequest);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody @Valid AuthRequest authRequest) {
        log.info("Пришёл запрос на login");
        return userService.login(authRequest);
    }
}
