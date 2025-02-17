package server.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.domain.entities.DeckCard;

@Repository
public interface DeckCardRepository extends JpaRepository<DeckCard, Long> {
}
