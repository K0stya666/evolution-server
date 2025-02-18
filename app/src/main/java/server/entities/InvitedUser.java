package server.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Invited_users")
public class InvitedUser {
    @EmbeddedId
    private InvitedUserId id;

    @MapsId("inviteId")
    @ManyToOne
    @JoinColumn(name = "invite_id", nullable = false)
    private Invite invite;

    @MapsId("invitedUserId")
    @ManyToOne
    @JoinColumn(name = "invited_id", nullable = false)
    private User invitedUser;
}
