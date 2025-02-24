package server.entities;

import jakarta.persistence.*;
import lombok.*;
import server.entities.Characteristic;
import server.entities.my_shit.characteristics.StatusCard;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "characteristic_id", referencedColumnName = "name", nullable = false)
    private Characteristic characteristic;

    /**
     * Это для указания одного из трёх перков - хищник, паразит, жирдяй
     */
    private StatusCard statusCard;
}
