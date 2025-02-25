package server.services.interfaces;

import server.cards.Card;

public interface DeckService {
    Card drawCard();
    void shuffleDeck();
    int getRemainingCards();
}
