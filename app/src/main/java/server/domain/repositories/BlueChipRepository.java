package server.domain.repositories;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.stereotype.Repository;
import server.domain.entities.BlueChip;

@Repository
public interface BlueChipRepository extends JpaAttributeConverter<BlueChip, Long> {
}
