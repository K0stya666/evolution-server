package server.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Red_chips_animals")
public class RedChipAnimal {
    @Id
    @Column(name = "red_chip")
    private Long redChipId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "red_chip", nullable = false)
    private RedChip redChip;

    @ManyToOne
    @JoinColumn(name = "animal")
    private Animal animal; // может быть null
}
