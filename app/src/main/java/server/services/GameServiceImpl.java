package server.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import server.entities.Game;
import server.repositories.GameRepository;
import server.services.interfaces.GameService;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    // Получаем список игр, находящихся в ожидании игроков
    @Override
    public List<Game> getAvailableGames() {
        return gameRepository.findByStatus("WAITING");
    }

    // Создаем новую игру, сразу добавляя создателя (первый игрок)
    @Override
    public Game createGame(int maxPlayers, Long creatorUserId) {
        Game newGame = Game.builder()
                .maxPlayers(maxPlayers)
                .currentPlayers(1)
                .status("WAITING")
                .build();
        gameRepository.save(newGame);
        gameRepository.flush();
        return newGame;
    }

    // Присоединение к игре
    @Override
    public Optional<Game> joinGame(Long gameId) {
        Optional<Game> gameOpt = gameRepository.findById(gameId);
        if (gameOpt.isPresent()) {
            Game game = gameOpt.get();
            // Можно присоединиться, если игра ожидает игроков и еще не заполнена
            if (game.getCurrentPlayers() < game.getMaxPlayers() && game.getStatus().equals("WAITING")) {
                game.setCurrentPlayers(game.getCurrentPlayers() + 1);
                // Если игроки заполнили игру – меняем статус
                if (game.getCurrentPlayers() == game.getMaxPlayers()) {
                    game.setStatus("STARTED");
                }
                gameRepository.save(game);
                return Optional.of(game);
            }
        }
        return Optional.empty();
    }
}
