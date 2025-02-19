package server.services.interfaces;

import server.entities.Game;
import server.entities.Player;
import server.entities.User;
import java.util.List;

public interface PlayerService {
    List<Player> getPlayers(Long gameId);
    Player createPlayer(Game game, User playerUser);
}
