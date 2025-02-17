package server.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Dump_cards")
public class DumpCard {
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
