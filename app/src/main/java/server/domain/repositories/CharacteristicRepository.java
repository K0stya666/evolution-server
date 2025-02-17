package server.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.domain.entities.Characteristic;

@Repository
public interface CharacteristicRepository extends JpaRepository<Characteristic, Long> {
}
