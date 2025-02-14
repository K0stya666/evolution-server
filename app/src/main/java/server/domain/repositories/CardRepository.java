package server.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.entities.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
}
