package server.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.entities.DeckCard;

public interface DeckCardRepository extends JpaRepository<DeckCard, Long> {
}
