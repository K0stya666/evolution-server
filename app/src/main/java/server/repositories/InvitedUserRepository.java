package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.entities.ochko.InvitedUser;
import server.entities.ochko.InvitedUserId;

@Repository
public interface InvitedUserRepository extends JpaRepository<InvitedUser, InvitedUserId> {
}
