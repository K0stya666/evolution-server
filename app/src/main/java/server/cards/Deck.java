package server.cards;

import java.util.Collections;
import java.util.LinkedList;

public class Deck {
    private final LinkedList<Card> cards;

    public Deck() {
        cards = new LinkedList<>();
        for (Condition condition : Condition.values()) {
            if (!condition.equals(Condition.PARASITE) && !condition.equals(Condition.COMMUNISM) && !condition.equals(Condition.BIG)) {
                Card card = new Card(condition, Perk.NONE);

                add4(card);
                if (condition.equals(Condition.SWIMMING)) {
                    add4(card);
                }
            }
            else {
                Card card = new Card(condition, Perk.PREDATOR);
                add4(card);

                card = new Card(condition, Perk.FAT);
                add4(card);
            }
        }

    }

    private void add4(Card card) {
        cards.add(card);
        cards.add(card);
        cards.add(card);
        cards.add(card);
    }

    private void shuffle() {
        Collections.shuffle(cards);
    }

    public int size() {
        return cards.size();
    }

    public Card takeCard() {
        return cards.isEmpty() ? null : cards.removeLast();
    }

}
