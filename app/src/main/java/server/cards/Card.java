package server.cards;

import lombok.*;

@Data
public class Card {
    private final Perk characteristic;

    /**
     * Это для указания одного из трёх перков - хищник, паразит, жирдяй
     */
    private final Type type;
}
