package server.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.domain.entities.InvitedUser;
import server.domain.entities.InvitedUserId;

@Repository
public interface InvitedUserRepository extends JpaRepository<InvitedUser, InvitedUserId> {
}
