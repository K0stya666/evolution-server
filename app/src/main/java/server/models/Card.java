package server.models;

import lombok.*;
import server.enums.Perk;
import server.enums.Type;

@Data
public class Card {
    private final Perk characteristic;

    /**
     * Это для указания одного из трёх перков - хищник, паразит, жирдяй
     */
    private final Type type;
}
