package server.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.domain.entities.DumpCard;

@Repository
public interface DumpCardRepository extends JpaRepository<DumpCard, Long> {
}
