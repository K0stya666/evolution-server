package server.services.interfaces;

import server.entities.ochko.DeckCard;

import java.util.List;

public interface DeckService {
    List<DeckCard> getDeck(Long gameId);
    List<DeckCard> shuffleDeck(Long gameId);
}
