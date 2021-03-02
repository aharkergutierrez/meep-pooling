package es.meep.tecnicaltest.database.no_relational.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import es.meep.tecnicaltest.database.no_relational.documents.MeepMobilityResourceDocument;

@Component
public interface MeepMobilityResourceDocumentRepository extends MongoRepository<MeepMobilityResourceDocument, String> {
	
	Optional<MeepMobilityResourceDocument> findByExternalId(String externalId);

	List<MeepMobilityResourceDocument> findByLongitudeBetweenAndLatitudeBetween(Double longitudeLow,
			Double longitudeTop, Double latitudeLow, Double latitudeTop);
	
}
