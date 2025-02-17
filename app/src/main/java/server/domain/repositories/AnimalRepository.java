package server.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.domain.entities.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
