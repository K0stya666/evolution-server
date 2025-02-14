package server.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.entities.DumpCard;

public interface DumpCardRepository extends JpaRepository<DumpCard, Long> {
}
