package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.entities.ochko.RedChipAnimal;

@Repository
public interface RedChipAnimalRepository extends JpaRepository<RedChipAnimal, Long> {
}
