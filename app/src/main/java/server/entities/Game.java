package server.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import server.cards.Battle;
import server.enums.Stage;
import server.enums.Status;

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

    @Column(name = "creator_id", nullable = false)
    private Long creatorId;

    @Column(name = "max_players", nullable = false)
    private int maxPlayers;

    @Column(name = "current_players", nullable = false)
    private int currentPlayers;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;


    @Getter
    @Transient
    @JsonIgnore
    private Battle battle;

    @PostLoad
    @PostPersist
    @PostUpdate
    private void initBattle() {
        if (battle == null) {
            battle = new Battle();
        }
    }

}
