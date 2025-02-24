package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.entities.ochko.DumpCard;

@Repository
public interface DumpCardRepository extends JpaRepository<DumpCard, Long> {
}
