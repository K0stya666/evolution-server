package server.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.domain.entities.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
