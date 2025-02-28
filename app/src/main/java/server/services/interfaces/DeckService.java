package server.services.interfaces;

import server.models.Card;

public interface DeckService {
    Card drawCard();
    void shuffleDeck();
    int getRemainingCards();
}
