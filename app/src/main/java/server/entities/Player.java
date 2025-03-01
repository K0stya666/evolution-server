package server.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import server.models.Card;
import java.util.LinkedList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Players",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"game_id", "user_id"}),
        }
)
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User playerUser;

    @Column(nullable = false, length = 12)
    private String name;

    @Transient
    @JsonIgnore
    private List<Card> cards;

    @Transient
    @JsonIgnore
    private List<Animal> animals;

    @PostLoad
    @PostPersist
    @PostUpdate
    public void initPlayersLogic() {
        if (cards == null)
            cards = new LinkedList<>();
        if (animals == null)
            animals = new LinkedList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }
}
