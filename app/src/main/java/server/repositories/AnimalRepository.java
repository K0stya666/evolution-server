package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.entities.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
