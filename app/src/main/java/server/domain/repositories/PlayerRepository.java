package server.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.entities.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
