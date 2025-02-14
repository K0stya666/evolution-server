package server.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.entities.InvitedUser;

public interface InvitedUserRepository extends JpaRepository<InvitedUser, Long> {
}
