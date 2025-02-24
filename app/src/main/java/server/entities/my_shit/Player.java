package server.entities.my_shit;

import jakarta.persistence.*;
import lombok.*;
import server.entities.Game;
import server.entities.User;


@Data
@AllArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    // Ссылка на игру
//    @ManyToOne
//    @JoinColumn(name = "game_id", nullable = false)
//    private Game game;

    // Ссылка на пользователя
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User playerUser;

    @Column(nullable = false, length = 12)
    private String name;
}
