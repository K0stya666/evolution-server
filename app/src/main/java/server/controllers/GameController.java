package server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.entities.Game;
import server.enums.Stage;
import server.services.interfaces.GameService;
import java.util.List;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173") // CORS для разработки
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<List<Game>> getAvailableGames() {
        return ResponseEntity.ok(gameService.getAvailableGames());
    }

    // Создаем новую игру с выбранным количеством игроков
    @PostMapping
    public ResponseEntity<Game> createGame(@RequestParam int maxPlayers) {
        // В реальном проекте получаем ID пользователя из SecurityContext
        Long creatorUserId = 1L; // Для примера
        Game game = gameService.createGame(maxPlayers, creatorUserId);
        return ResponseEntity.ok(game);
    }

    // Присоединяемся к игре
    @PostMapping("/{gameId}/join")
    public ResponseEntity<Game> joinGame(@PathVariable Long gameId) {
        return gameService.joinGame(gameId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

//    @GetMapping("/{id}")
//    public Game getGame(@PathVariable Long id) {
//        return gameService.getGame(id);
//    }

//    @PutMapping("/{id}/stage")
//    public Game updateGameStage(@PathVariable Long id,
//                                @RequestParam Stage newStage) {
//        return gameService.updateGameStage(id, newStage);
//    }
}
