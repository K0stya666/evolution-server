package server.services.interfaces;

import server.cards.Battle;
import server.cards.Card;
import server.cards.Deck;
import server.entities.Game;
import server.entities.Player;

import java.util.List;
import java.util.Optional;

public interface GameService {
    List<Game> getAvailableGames();
    Game createGame(int maxPlayers, Long creatorUserId);
    Optional<Game> joinGame(Long gameId, Long userId);
    List<Player> getPlayers(Long gameId);
//    Battle getBattle(Long gameId);
}
