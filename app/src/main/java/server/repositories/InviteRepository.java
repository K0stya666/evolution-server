package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.entities.Invite;

@Repository
public interface InviteRepository extends JpaRepository<Invite, Long> {
}
