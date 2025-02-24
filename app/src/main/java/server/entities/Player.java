package server.entities;

import jakarta.persistence.*;
import lombok.*;

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
    private List<Card> cards;
}
