package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.entities.DeckCard;

@Repository
public interface DeckCardRepository extends JpaRepository<DeckCard, Long> {
}
