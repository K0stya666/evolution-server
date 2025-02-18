package server.services;

import server.dto.UserDTO;
import server.entities.User;
import server.network.*;

public interface UserService {
    User create(User user);
    UserDTO getCurrentUser();
    AuthResponse login(AuthRequest authRequest);
    AuthResponse register(AuthRequest authRequest);
}
