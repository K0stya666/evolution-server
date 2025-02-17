package server.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.domain.entities.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
}
