package es.meep.tecnicaltest.database.relational.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import es.meep.tecnicaltest.database.relational.entities.MeepMobilityResourceEntity;

@Profile({ "relationaldb", "test" })
public interface AccessDataMeepMobilityResourceEntity extends JpaRepository<MeepMobilityResourceEntity, Integer> {

	Optional<MeepMobilityResourceEntity> findByExternalId(String externalId);

	List<MeepMobilityResourceEntity> findByLongitudeBetweenAndLatitudeBetween(BigDecimal longitudeLow,
			BigDecimal longitudeTop, BigDecimal latitudeLow, BigDecimal latitudeTop);

}
