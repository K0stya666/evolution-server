package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.entities.ochko.RedChip;

@Repository
public interface RedChipRepository extends JpaRepository<RedChip, Long> {
}
