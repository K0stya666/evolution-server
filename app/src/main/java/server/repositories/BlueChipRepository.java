package server.repositories;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.stereotype.Repository;
import server.entities.ochko.BlueChip;

@Repository
public interface BlueChipRepository extends JpaAttributeConverter<BlueChip, Long> {
}
