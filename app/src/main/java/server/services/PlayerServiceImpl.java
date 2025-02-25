package server.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import server.entities.Game;
import server.entities.Player;
import server.entities.User;
import server.repositories.PlayerRepository;
import server.services.interfaces.PlayerService;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    @Override
    public Player createPlayer(Game game, User playerUser) {
        Player player = Player.builder()
                .game(game)
                .playerUser(playerUser)
                .name(playerUser.getLogin())
                .build();
        return playerRepository.save(player);
    }
}
