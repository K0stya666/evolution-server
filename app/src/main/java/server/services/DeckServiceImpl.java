package server.services;

import lombok.Data;
import org.springframework.stereotype.Service;
import server.models.Card;
import server.models.Deck;
import server.services.interfaces.DeckService;

@Data
@Service
public class DeckServiceImpl implements DeckService {

    private final Deck deck;

    public DeckServiceImpl() {
        this.deck = new Deck();
        deck.shuffle(); // Перемешиваем колоду при инициализации сервиса
    }

    /**
     * Метод для получения карты из колоды.
     * @return следующая карта или null, если колода пуста.
     */
    @Override
    public synchronized Card drawCard() {
        return deck.takeCard();
    }

    /**
     * Метод для перемешивания колоды.
     */
    @Override
    public synchronized void shuffleDeck() {
        deck.shuffle();
    }

    /**
     * Возвращает количество оставшихся карт в колоде.
     * @return размер колоды.
     */
    @Override
    public int getRemainingCards() {
        return deck.size();
    }
}
