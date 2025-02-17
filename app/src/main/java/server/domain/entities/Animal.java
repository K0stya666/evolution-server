package server.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Animals")
public class Animal {
    @Id
    @Column(name = "card")
    private Long cardId;

    @OneToOne
    @MapsId // PK = cardId = Card.id
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;
}
