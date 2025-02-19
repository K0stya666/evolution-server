package server.services.interfaces;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.entities.Game;
import server.enums.Stage;
import server.repositories.GameRepository;
import java.util.List;
import java.util.Optional;

public interface GameService {
    List<Game> getAvailableGames();
    Game createGame(int maxPlayers, Long creatorUserId);
    Optional<Game> joinGame(Long gameId);
}
