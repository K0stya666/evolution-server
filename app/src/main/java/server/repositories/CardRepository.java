package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.entities.Card;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
//    List<Card> findByDeckCard(Long id);
}
