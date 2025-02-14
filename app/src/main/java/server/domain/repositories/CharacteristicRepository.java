package server.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.entities.Characteristic;

public interface CharacteristicRepository extends JpaRepository<Characteristic, Long> {
}
