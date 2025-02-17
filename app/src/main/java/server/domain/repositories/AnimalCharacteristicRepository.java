package server.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.domain.entities.AnimalCharacteristic;

@Repository
public interface AnimalCharacteristicRepository extends JpaRepository<AnimalCharacteristic, Long> {
}
