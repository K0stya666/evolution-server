package server.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import server.network.AuthRequest;
import server.network.AuthResponse;
import server.services.UserService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

//    private final System.Logger logger = System.getLogger("AuthController");
    private final UserService userService;
//    private final SimpMessagingTemplate messagingTemplate;

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



//    @PostMapping("/register")
//    @SendTo("/topic/registration")
//    public User registerUser(@RequestBody AuthRequest authRequest) {
//        User registeredUser = userService.registerUser(authRequest);
////        System.Logger.Level FlavorEvent = null;
////        logger.log(FlavorEvent, "внутри");
//        messagingTemplate.convertAndSend("/topic/user/" + registeredUser.getId(),
//                Map.of("message", "User registered", "userId", registeredUser.getId()));
//        return registeredUser;
//    }
//
//    @PostMapping("/login")
//    @SendTo("/topic/loginResult")
//    public User loginUser(@RequestBody AuthRequest authRequest) {
//        Optional<User> userOpt = userService.authenticate(authRequest.getLogin(), authRequest.getPassword());
//        if (userOpt.isEmpty()) {
//            throw new RuntimeException("Invalid credentials");
//        }
//        User user = userOpt.get();
//
//        // Отправка WebSocket-сообщения после успешного входа
//        messagingTemplate.convertAndSend("/topic/user/" + user.getId(),
//                Map.of("message", "User logged in", "userId", user.getId()));
//        return user;
//    }
}
