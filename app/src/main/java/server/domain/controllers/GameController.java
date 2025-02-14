package server.domain.controllers;

import org.springframework.web.bind.annotation.*;
import server.domain.entities.Game;
import server.domain.enums.Stage;
import server.domain.services.GameService;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public List<Game> getAllGames() {
        return gameService.findAllGames();
    }

    @PostMapping
    public Game createGame() {
        return gameService.createGame();
    }

    @GetMapping("/{id}")
    public Game getGame(@PathVariable Long id) {
        return gameService.getGame(id);
    }

    @PutMapping("/{id}/stage")
    public Game updateGameStage(@PathVariable Long id,
                                @RequestParam Stage newStage) {
        return gameService.updateGameStage(id, newStage);
    }
}
