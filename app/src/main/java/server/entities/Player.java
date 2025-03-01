package server.entities;

import jakarta.persistence.*;
import lombok.*;

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

    // Ссылка на игру
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    // Ссылка на пользователя
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User playerUser;

    @Column(nullable = false, length = 12)
    private String name;
}
