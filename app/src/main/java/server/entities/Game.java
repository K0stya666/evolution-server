package server.entities;

import jakarta.persistence.*;
import lombok.*;
import server.enums.Stage;

@Builder
@Getter
@Setter
@Data
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

    @Transient
    private int maxPlayers;

    @Transient
    private int currentPlayers;

    @Transient
    private String status;
}