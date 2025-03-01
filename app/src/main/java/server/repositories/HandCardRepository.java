package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.entities.HandCard;

@Repository
public interface HandCardRepository extends JpaRepository<HandCard, Long> {
}
