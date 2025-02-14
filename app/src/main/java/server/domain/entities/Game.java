package server.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import server.domain.enums.Stage;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Stage stage;

    @Column(name = "dice_number")
    private Integer diceNumber;
}