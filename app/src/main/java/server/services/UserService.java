package server.services;

import server.dto.UserDTO;
import server.entities.User;
import server.network.*;

public interface UserService {
    User create(User user);
    UserDTO getCurrentUser();
    AuthResponse login(AuthRequest authRequest);
    AuthResponse register(AuthRequest authRequest);


//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
////    private final JwtUtil jwtUtil;
//
//    public UserService(UserRepository userRepository,
//                       PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
////    @Override
////    public User create
//
//    public User registerUser(AuthRequest request) {
//        // Проверяем, есть ли уже такой пользователь
//        if (userRepository.findByLogin(request.getLogin()).isPresent()) {
//            throw new RuntimeException("Пользователь с логином " + request.getLogin() + " уже существует");
//        }
//
//        String login = request.getLogin();
//        String hashed = passwordEncoder.encode(request.getPassword());
//
//        // Создаём и сохраняем нового пользователя
//        User newUser = new User(login, hashed);
//        return userRepository.save(newUser);
//    }
//
//    public Optional<User> authenticate(String login, String password) {
//        return userRepository.findByLogin(login)
//                .filter(user -> passwordEncoder.matches(password, user.getPassword()));
//    }
}
