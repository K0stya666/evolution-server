package server.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.entities.AnimalCharacteristic;

public interface AnimalCharacteristicRepository extends JpaRepository<AnimalCharacteristic, Long> {
}
