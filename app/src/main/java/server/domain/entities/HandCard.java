package server.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Hand_cards")
public class HandCard {
    @Id
    @Column(name = "card")
    private Long cardId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;
}
