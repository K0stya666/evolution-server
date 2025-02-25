package server.services.interfaces;

import server.entities.*;

public interface PlayerService {
    Player createPlayer(Game game, User playerUser);
}
