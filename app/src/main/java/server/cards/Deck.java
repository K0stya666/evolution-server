package server.cards;

import java.util.Collections;
import java.util.LinkedList;

public class Deck {
    private final LinkedList<Card> cards;

    public Deck() {
        cards = new LinkedList<>();
        for (Perk perk : Perk.values()) {
            if (!perk.equals(Perk.PARASITE) && !perk.equals(Perk.COMMUNISM) && !perk.equals(Perk.BIG)) {
                Card card = new Card(perk, Type.NONE);

                add4(card);
                if (perk.equals(Perk.SWIMMING)) {
                    add4(card);
                }
            }
            else {
                Card card = new Card(perk, Type.PREDATOR);
                add4(card);

                card = new Card(perk, Type.FAT);
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
