package server.domain.repositories;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import server.domain.entities.BlueChip;

public interface BlueChipRepository extends JpaAttributeConverter<BlueChip, Long> {
}
