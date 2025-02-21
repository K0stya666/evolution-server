package server.entities;

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

    @ManyToOne
    @MapsId
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    @OneToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;
}
