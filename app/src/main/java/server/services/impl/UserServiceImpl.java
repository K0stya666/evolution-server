package server.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import server.entities.User;
import server.network.AuthRequest;
import server.network.AuthResponse;
import server.repositories.UserRepository;
import server.services.interfaces.UserService;
import server.utils.JwtUtil;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public User createUser(User user) {
        // If you prefer to hash the password in a "register" method,
        // you can do it there. For now, we'll assume it's already set.
        return userRepository.save(user);
    }

    @Override
    public AuthResponse login(AuthRequest logRequest) {
        // 1) Find user by login
        User user = userRepository.findByLogin(logRequest.login())
                .orElseThrow(() -> new UsernameNotFoundException("Bad username or password"));

        // 2) Check password hash
        if (!passwordEncoder.matches(logRequest.password(), user.getPassword())) {
            throw new UsernameNotFoundException("Bad username or password");
        }

        // 3) Generate JWT
        String token = jwtUtil.createToken(user);
        log.info("Generated token for user {}: {}", user.getLogin(), token);

        // 4) Return AuthResponse with token
        return new AuthResponse(token);
    }

    @Override
    public AuthResponse register(AuthRequest regRequest) {
        // 1) Check if user already exists
        Optional<User> existingUser = userRepository.findByLogin(regRequest.login());
        if (existingUser.isPresent()) {
            // Login already taken
            log.warn("User with login '{}' already exists.", regRequest.login());
            // Return null token or throw an exception
            return new AuthResponse(null);
        }

        // 2) Create a new User, hash the password
        User newUser = User.builder()
                .login(regRequest.login())
                .password(passwordEncoder.encode(regRequest.password()))
                .build();
        userRepository.save(newUser);

        // 3) Generate JWT for newly registered user
        String token = jwtUtil.createToken(newUser);
        log.info("Generated token for new user {}: {}", newUser.getLogin(), token);

        // 4) Return AuthResponse with token
        return new AuthResponse(token);
    }
}
