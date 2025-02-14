package server.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.entities.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
}
