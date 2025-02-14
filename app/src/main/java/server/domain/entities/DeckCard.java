package server.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Deck_cards")
public class DeckCard {
    @Id
    @Column(name = "card")
    private Long cardId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "card", nullable = false)
    private Card card;

    @ManyToOne
    @JoinColumn(name = "game", nullable = false)
    private Game game;
}
