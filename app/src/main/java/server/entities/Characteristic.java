package server.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Characteristics")
public class Characteristic {
    @Id
    @Column(length = 30)
    private String name;  // PRIMARY KEY (name)
}
