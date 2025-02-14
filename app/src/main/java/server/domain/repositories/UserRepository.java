package server.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
