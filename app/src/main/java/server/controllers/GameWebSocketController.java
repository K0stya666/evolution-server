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

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class GameWebSocketController {
    private final GameService gameService;
    private final UserService userService;

    @MessageMapping("/games/createGame")
    @SendTo("/topic/games")
    public Game createGame(@Payload int maxPlayers, Principal principal) {
        // principal.getName() is what you set in StompChannelInterceptor
        if ("anonymous".equals(principal.getName()))
            throw new RuntimeException("User must be logged in to create game");

        Long userId = Long.valueOf(principal.getName());
        return gameService.createGame(maxPlayers, userId);
    }


    @MessageMapping("/games/joinGame")
    @SendTo("/topic/games")
    public Game joinGame(@Payload Long gameId, Principal principal) {
        if ("anonymous".equals(principal.getName()))
            throw new RuntimeException("User must be logged in to create game");

        Long userId = Long.valueOf(principal.getName());
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
