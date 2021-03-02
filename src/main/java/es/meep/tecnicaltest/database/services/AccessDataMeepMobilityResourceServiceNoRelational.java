package es.meep.tecnicaltest.database.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import es.meep.tecnicaltest.database.no_relational.repositories.MeepMobilityResourceDocumentRepository;
import es.meep.tecnicaltest.model.MeepMobilityResource;
import es.meep.tecnicaltest.model.mappers.MeepMobilityResourceMapper;

@Service
@Profile({ "norelationaldb" })
public class AccessDataMeepMobilityResourceServiceNoRelational extends IAccessDataMeepMobilityResourceService {

	@Autowired
	private MeepMobilityResourceDocumentRepository meepMobilityResourceDocumentRepository;

	@Autowired
	private MeepMobilityResourceMapper meepMobilityResourceMapper;

	public MeepMobilityResource saveMeepMobilityResourceInBD(MeepMobilityResource meepMobilityResource) {

		meepMobilityResourceDocumentRepository.findByExternalId(meepMobilityResource.getExternalId())
				.ifPresent(entity -> {
					meepMobilityResource.setOwnId(entity.getId());
				});
		return meepMobilityResourceMapper.meepMobilityResourceEntityToMeepMobilityResource(
				meepMobilityResourceDocumentRepository.save(meepMobilityResourceMapper
						.meepMobilityResourceToMeepMobilityResourceDocument(meepMobilityResource)));
	}

	public List<MeepMobilityResource> getResourcesInRange(Double longitude, Double latitude, Integer meters) {

		Double[] extremePoints = calculePositions(latitude, longitude, meters.doubleValue(), meters.doubleValue());

		return meepMobilityResourceDocumentRepository
				.findByLongitudeBetweenAndLatitudeBetween(extremePoints[1], extremePoints[3], extremePoints[0],
						extremePoints[2])
				.stream().map(r -> meepMobilityResourceMapper.meepMobilityResourceEntityToMeepMobilityResource(r))
				.collect(Collectors.toList());
	}

}
