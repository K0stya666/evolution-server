package server.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.entities.InvitedUserId;

public interface InvitedUserIdRepository extends JpaRepository<InvitedUserId, Long> {
}
