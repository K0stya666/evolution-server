package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.entities.AnimalCharacteristic;

@Repository
public interface AnimalCharacteristicRepository extends JpaRepository<AnimalCharacteristic, Long> {
}
