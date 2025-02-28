package server.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import server.models.Deck;
import server.entities.Game;
import server.entities.Player;
import server.entities.User;
import server.repositories.GameRepository;
import server.repositories.PlayerRepository;
import server.repositories.UserRepository;
import server.services.interfaces.GameService;
import java.util.List;
import java.util.Optional;
import static server.enums.Stage.GROWTH;
import static server.enums.Status.STARTED;
import static server.enums.Status.WAITING;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final UserRepository userRepository;

    private final PlayerServiceImpl playerService;
    private final DeckServiceImpl deckService;

    // Получаем список игр, находящихся в ожидании игроков
    @Override
    public List<Game> getAvailableGames() {
        return gameRepository.findByStatus(WAITING);
    }

    // Создаем новую игру, сразу добавляя создателя (первый игрок)
    @Override
    public Game createGame(int maxPlayers, Long creatorUserId) {
        if (maxPlayers < 2 || maxPlayers > 4) {
            throw new IllegalArgumentException("Количество игроков должно быть от 2 до 4");
        }

        deckService.shuffleDeck();

        Game newGame = Game.builder()
                .stage(GROWTH)
                .maxPlayers(maxPlayers)
                .creatorId(creatorUserId)
                .maxPlayers(maxPlayers)
                .currentPlayers(1)
                .status(WAITING)
                .build();
        gameRepository.save(newGame);
        gameRepository.flush();

        User creator = userRepository.findById(creatorUserId).get();
        Player newPlayer = playerService.createPlayer(newGame, creator);
        playerRepository.save(newPlayer);
        playerRepository.flush();

        log.info("Создана игра с id {} и максимальным количеством игроков {}", newGame.getId(), maxPlayers);
        return newGame;
    }

    // Присоединение к игре
    @Override
    public Optional<Game> joinGame(Long gameId, Long userId) {
        Optional<Game> gameOpt = gameRepository.findById(gameId);
        User user = userRepository.findById(userId).get();

        playerService.createPlayer(gameOpt.get(), user);


        Game game = gameOpt.get();
        // Можно присоединиться, если игра ожидает игроков и еще не заполнена
        if (game.getCurrentPlayers() < game.getMaxPlayers() && game.getStatus().equals(WAITING)) {
            game.setCurrentPlayers(game.getCurrentPlayers() + 1);
            // Если игроки заполнили игру – меняем статус
            if (game.getCurrentPlayers() == game.getMaxPlayers()) {
                game.setStatus(STARTED);
            }
            gameRepository.save(game);
            log.info("Пользователь {} присоединился к игре с id {}", userId, gameId);
            return Optional.of(game);
        }
        return Optional.empty();
    }

    @Override
    public List<Player> getPlayers(Long gameId) {
        return playerRepository.findByGameId(gameId);
    }

    @Override
    public Deck getDeck() {
        return deckService.getDeck();
    }
}
