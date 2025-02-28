package server.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import server.cards.Battle;
import server.cards.Deck;
import server.enums.Stage;
import server.enums.Status;

import java.util.LinkedList;

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

    @Transient
    @JsonIgnore
    private Battle battle;

    /**
     * Инициализация transient-полей после загрузки/сохранения сущности.
     */
    @PostLoad
    @PostPersist
    @PostUpdate
    public void initGameLogic() {
        if (battle == null) {
            battle = new Battle();
//            battle.startGame();
        }
    }

    /**
     * Добавление игрока в игру
     */
    public void addPlayer(Player player) {
        battle.addPlayer(player);
        currentPlayers = battle.countPlayers();
    }



}
