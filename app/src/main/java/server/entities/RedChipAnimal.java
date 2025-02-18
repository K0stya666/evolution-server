package server.entities;

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
    @JoinColumn(name = "red_chip_id", nullable = false)
    private RedChip redChip;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal; // может быть null
}
