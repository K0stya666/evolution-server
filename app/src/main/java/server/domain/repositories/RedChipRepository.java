package server.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.entities.RedChip;

public interface RedChipRepository extends JpaRepository<RedChip, Long> {
}
