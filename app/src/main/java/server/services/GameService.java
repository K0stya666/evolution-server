package server.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.entities.Game;
import server.enums.Stage;
import server.repositories.GameRepository;

import java.util.List;

@Service
@Transactional
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> findAllGames() {
        return gameRepository.findAll();
    }

    public Game createGame() {
        Game game = new Game();
        game.setStage(Stage.GROWTH);
        game.setDiceNumber(null);
        return gameRepository.save(game);
    }

    public Game getGame(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Игра не найдена, id=" + id));
    }

    public Game updateGameStage(Long id, Stage newStage) {
        Game game = getGame(id);
        game.setStage(newStage);
        return gameRepository.save(game);
    }
}
