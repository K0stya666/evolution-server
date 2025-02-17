package server.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.domain.entities.RedChipAnimal;

@Repository
public interface RedChipAnimalRepository extends JpaRepository<RedChipAnimal, Long> {
}
