package server.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.entities.ochko.DeckCard;
import server.repositories.DeckCardRepository;
import server.services.interfaces.DeckService;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeckServiceImpl implements DeckService {

    private final DeckCardRepository deckCardRepository;

    @Override
    public List<DeckCard> getDeck(Long gameId) {
        return deckCardRepository.findByGameId(gameId);
    }

//    @Transactional
//    DeckCard drawCard(Long gameId) {
//        List<DeckCard> deck = getDeck(gameId);
//        if (deck.isEmpty()) {
//            throw new RuntimeException("Колода пуста");
//        }
//        // Предположим, что верхняя карта находится в начале списка
//        DeckCard drawn = deck.first();
//        deckCardRepository.delete(drawn);
//        return drawn;
//    }

    @Transactional
    @Override
    public List<DeckCard> shuffleDeck(Long gameId) {
        List<DeckCard> deck = getDeck(gameId);
        Collections.shuffle(deck);
        // Если требуется сохранить порядок в БД, можно обновить соответствующее поле
        // В этом примере порядок просто перемешивается в оперативной памяти
        return deck;
    }

}
