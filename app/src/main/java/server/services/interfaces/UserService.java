package server.services.interfaces;

import server.entities.User;
import server.network.*;

public interface UserService {
    User createUser(User user);
//    UserDTO getCurrentUser();
    Long getUserIdFromToken();
    AuthResponse login(AuthRequest authRequest);
    AuthResponse register(AuthRequest authRequest);
}
