package server.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.cards.Battle;
import server.cards.Card;
import server.cards.Condition;
import server.cards.Perk;
import server.entities.Game;
import server.entities.Player;
import server.services.interfaces.GameService;
import server.services.interfaces.UserService;

import java.lang.reflect.Type;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173") // CORS для разработки
public class GameController {
    private final GameService gameService;
    private final UserService userService;

    @GetMapping("/availableGames")
    public ResponseEntity<List<Game>> getAvailableGames() {
        return ResponseEntity.ok(gameService.getAvailableGames());
    }

    // Создаем новую игру с выбранным количеством игроков
    @PostMapping("/createGame")
    public ResponseEntity<Game> createGame(@RequestParam int maxPlayers) {
        Long creatorUserId = userService.getUserIdFromToken(); // Для примера
        Game game = gameService.createGame(maxPlayers, creatorUserId);
        return ResponseEntity.ok(game);
    }

    // Присоединяемся к игре
    @PostMapping("/{gameId}/join")
    public ResponseEntity<Game> joinGame(@PathVariable Long gameId) {
        Long userId = userService.getUserIdFromToken();
        return gameService.joinGame(gameId, userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/{gameId}/getPlayers")
    public ResponseEntity<List<Player>> getPlayers(@PathVariable Long gameId) {
        List<Player> players = gameService.getPlayers(gameId);
        return ResponseEntity.ok(players);
    }

    @GetMapping("/{gameId}/getCard")
    public ResponseEntity<Card> getCard(@PathVariable Long gameId) {
        Battle BIBCHICK = gameService.getBattle(gameId);
        Card card = BIBCHICK.getDeck().takeCard();
        return ResponseEntity.ok(card);
    }


    @GetMapping("/{gameId}/getCards")
    public ResponseEntity<List<Card>> getCards(@PathVariable Long gameId) {
        Battle bibchick = gameService.getBattle(gameId);
//        bibchick.giveCards();
//        List<Card> cards = bibchick.getPlayers().get(0).getCards();

        List<Card> cards = List.of(new Card(Condition.BIG, Perk.FAT), new Card(Condition.COMMUNISM, Perk.NONE), new Card(Condition.BIG, Perk.PREDATOR));
        log.info("карты: {}", cards);
        log.info("Игроки: {}", bibchick.getPlayers());
        return ResponseEntity.ok(cards);
    }


//    @GetMapping("/{id}")
//    public Battle getGame(@PathVariable Long id) {
//        return gameService.getGame(id);
//    }

//    @PutMapping("/{id}/stage")
//    public Battle updateGameStage(@PathVariable Long id,
//                                @RequestParam Stage newStage) {
//        return gameService.updateGameStage(id, newStage);
//    }
}
