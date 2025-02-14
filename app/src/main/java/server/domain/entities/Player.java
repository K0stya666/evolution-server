package server.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Players",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"game", "user"}),
                @UniqueConstraint(columnNames = {"game", "name"})
        }
)
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Ссылка на игру
    @ManyToOne
    @JoinColumn(name = "game", nullable = false)
    private Game game;

    // Ссылка на пользователя
    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private User user;

    @Column(nullable = false, length = 12)
    private String name;
}
