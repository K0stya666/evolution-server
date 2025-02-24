package server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.entities.ochko.DeckCard;
import server.services.interfaces.DeckService;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/deck")
public class DeckController {

    private final DeckService deckService;

    @GetMapping("/{gameId}")
    public ResponseEntity<List<DeckCard>> getDeck(@PathVariable Long gameId) {
        List<DeckCard> deck = deckService.getDeck(gameId);
        return ResponseEntity.ok(deck);
    }

    @PostMapping("/{gameId}/shuffle")
    public ResponseEntity<List<DeckCard>> shuffleDeck(@PathVariable Long gameId) {
        List<DeckCard> shuffled = deckService.shuffleDeck(gameId);
        return ResponseEntity.ok(shuffled);
    }
}
