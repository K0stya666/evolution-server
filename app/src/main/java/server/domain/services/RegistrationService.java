package server.domain.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import server.domain.entities.User;
import server.domain.repositories.UserRepository;

@Service
public class RegistrationService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public RegistrationService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean registerUser(String username, String rawPassword) {
        // Проверяем, есть ли уже такой пользователь
        if (userRepository.findByUsername(username).isPresent()) {
            return false; // пользователь существует
        }

        // Хэшируем пароль
        String hashed = passwordEncoder.encode(rawPassword);

        // Создаём и сохраняем нового пользователя
        User newUser = new User(username, hashed);
        userRepository.save(newUser);

        return true;
    }
}
