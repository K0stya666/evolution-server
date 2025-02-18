package server.services;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import server.dto.UserDTO;
import server.entities.User;
import server.network.AuthResponse;
import server.network.AuthRequest;
import server.repositories.UserRepository;
import server.utils.JwtUtil;
import java.util.Comparator;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public User create(User user) {
        User newUser = userRepository.save(user);
        userRepository.flush();
        return newUser;
    }

    @Override
    public UserDTO getCurrentUser() {
        return userRepository.findById(getUserIdFromToken())
                .stream()
                .max(Comparator.comparingLong(User::getId))
                .map(user -> new UserDTO(user.getId(), user.getLogin())).get();

    }

    public Long getUserIdFromToken() {
        Claims credentials = (Claims) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        log.info("user id {}", credentials.get("id"));
        return Long.parseLong(credentials.get("id").toString());
    }

    @Override
    public AuthResponse login(AuthRequest logRequest) {
        User user = userRepository.findByLogin(logRequest.login()).get();

        if (!passwordEncoder.matches(logRequest.password(), user.getPassword())) {
            throw new UsernameNotFoundException("Неверное имя пользователя или пароль");
        }

        String token = jwtUtil.createToken(user);
        log.info("token, который сгенерировался = {}", token);
        userRepository.save(user);

        return new AuthResponse(token);
    }

    @Override
    public AuthResponse register(AuthRequest regRequest) {
        Optional<User> checkUser = userRepository.findByLogin(regRequest.login());
        try {
            if (checkUser.isEmpty()) {

                User user = User.builder()
                        .password(passwordEncoder.encode(regRequest.password()))
                        .build();
                log.info("Создание нового юзера {}", user.getLogin());
                User newUser = create(user);
                String token = jwtUtil.createToken(newUser);


                return new AuthResponse(token);
            } else return new AuthResponse(null);
        } catch (Exception e) {
            log.error("Error in register service");
            e.printStackTrace();
            return new AuthResponse(null);
        }
    }
}
