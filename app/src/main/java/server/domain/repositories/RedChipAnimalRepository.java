package server.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.entities.RedChipAnimal;

public interface RedChipAnimalRepository extends JpaRepository<RedChipAnimal, Long> {
}
