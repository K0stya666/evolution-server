package server.domain.controllers.registration;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import server.domain.services.RegistrationService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RegistrationController {
    private final List<String> registeredUsers = new ArrayList<>();
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @MessageMapping("/register")      // клиент отправляет на /app/register
    @SendTo("/topic/registerResult")  // ответ уходит подписчикам /topic/registerResult
    public RegisterResponse handleRegistration(RegisterMessage message) {
        RegisterResponse response = new RegisterResponse();

        boolean success = registrationService.registerUser(
                message.getUsername(),
                message.getPassword()
        );

        if (success) {
            response.setStatus("success");
        } else {
            response.setStatus("error");
            response.setError("Пользователь уже существует");
        }

        return response;
    }
}
