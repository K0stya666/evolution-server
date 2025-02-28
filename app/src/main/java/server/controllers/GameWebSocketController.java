package server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import server.cards.Battle;
import server.entities.Game;
import server.entities.Player;
import server.models.Card;
import server.models.Deck;
import server.services.interfaces.GameService;
import server.services.interfaces.UserService;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class GameWebSocketController {
    private final GameService gameService;
    private final UserService userService;

    @MessageMapping("/games/createGame")
    @SendTo("/topic/games") // Рассылаем созданную игру всем подписчикам
    public Game createGame(@Payload int maxPlayers) {
        Long creatorUserId = userService.getUserIdFromToken(); // Получение id пользователя из токена (можно извлечь из заголовков)
        return gameService.createGame(maxPlayers, creatorUserId);
    }

    @MessageMapping("/games/joinGame")
    @SendTo("/topic/games")
    public Game joinGame(@Payload Long gameId) {
        Long userId = userService.getUserIdFromToken();
        return gameService.joinGame(gameId, userId)
                .orElseThrow(() -> new RuntimeException("Не удалось присоединиться к игре"));
    }

    @MessageMapping("/games/getPlayers")
    @SendToUser("/queue/players")
    public List<Player> getPlayers(@Payload Long gameId) {
        return gameService.getPlayers(gameId);
    }

    @MessageMapping("/games/getDeck")
    @SendToUser("/queue/deck")
    public Deck getDeck() {
        return gameService.getDeck();
    }

    @MessageMapping("/games/getCards")
    @SendToUser("/queue/cards")
    public List<Card> getCards(@Payload Long gameId) {
        Battle bibchick = gameService.getBattle(gameId);
        bibchick.giveCards();
        return bibchick.getPlayers().get(0).getCards();
    }
}
