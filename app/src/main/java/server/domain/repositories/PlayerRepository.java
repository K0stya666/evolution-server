package server.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.domain.entities.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
