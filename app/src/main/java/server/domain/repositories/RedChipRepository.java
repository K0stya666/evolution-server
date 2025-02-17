package server.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.domain.entities.RedChip;

@Repository
public interface RedChipRepository extends JpaRepository<RedChip, Long> {
}
