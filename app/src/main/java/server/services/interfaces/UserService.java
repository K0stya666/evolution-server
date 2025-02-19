package server.services.interfaces;

import server.dto.UserDTO;
import server.entities.User;
import server.network.*;

public interface UserService {
    User createUser(User user);
//    UserDTO getCurrentUser();
    AuthResponse login(AuthRequest authRequest);
    AuthResponse register(AuthRequest authRequest);
}
