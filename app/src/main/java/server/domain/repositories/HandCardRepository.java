package server.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.entities.HandCard;

public interface HandCardRepository extends JpaRepository<HandCard, Long> {
}
