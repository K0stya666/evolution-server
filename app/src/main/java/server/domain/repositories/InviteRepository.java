package server.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.entities.Invite;

public interface InviteRepository extends JpaRepository<Invite, Long> {
}
