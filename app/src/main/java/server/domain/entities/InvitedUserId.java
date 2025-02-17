package server.domain.entities;

import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;

//@Getter
//@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class InvitedUserId implements Serializable {
    private Long invited;  // user id
    private Long invite;   // invite id
}
