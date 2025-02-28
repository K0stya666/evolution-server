package server.cards;

import lombok.*;

@Data
public class Card {
    private final Condition condition;

    /**
     * Это для указания одного из трёх перков - хищник, паразит, жирдяй
     */
    private final Perk perk;
}
