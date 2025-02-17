package server.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Animal_characteristics")
public class AnimalCharacteristic {
    @Id
    @Column(name = "card")
    private Long cardId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;
}
