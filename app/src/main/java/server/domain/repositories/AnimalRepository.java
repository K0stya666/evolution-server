package server.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.entities.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
