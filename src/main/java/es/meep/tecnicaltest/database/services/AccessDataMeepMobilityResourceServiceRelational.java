package es.meep.tecnicaltest.database.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import es.meep.tecnicaltest.database.relational.entities.MeepMobilityResourceEntity;
import es.meep.tecnicaltest.database.relational.repositories.AccessDataMeepMobilityResourceEntity;
import es.meep.tecnicaltest.model.MeepMobilityResource;
import es.meep.tecnicaltest.model.mappers.MeepMobilityResourceMapper;

@Service
@Profile({ "relationaldb", "test" })
public class AccessDataMeepMobilityResourceServiceRelational extends IAccessDataMeepMobilityResourceService{

	@Autowired
	private AccessDataMeepMobilityResourceEntity accessDataMeepMobilityResourceEntity;

	@Autowired
	private MeepMobilityResourceMapper meepMobilityResourceMapper;

	public MeepMobilityResource saveMeepMobilityResourceInBD(MeepMobilityResource meepMobilityResource) {

		accessDataMeepMobilityResourceEntity.findByExternalId(meepMobilityResource.getExternalId())
				.ifPresent(entity -> {
					meepMobilityResource.setOwnId(""+entity.getId());
				});

		return meepMobilityResourceMapper.meepMobilityResourceEntityToMeepMobilityResource(
				accessDataMeepMobilityResourceEntity.save(meepMobilityResourceMapper
						.meepMobilityResourceToMeepMobilityResourceEntity(meepMobilityResource)));
	}

	public List<MeepMobilityResource> getResourcesInRange(Double longitude, Double latitude, Integer meters) {

		Double[] extremePoints = calculePositions(latitude, longitude, meters.doubleValue(), meters.doubleValue());

		return accessDataMeepMobilityResourceEntity
				.findByLongitudeBetweenAndLatitudeBetween(new BigDecimal(extremePoints[1]),
						new BigDecimal(extremePoints[3]), new BigDecimal(extremePoints[0]),
						new BigDecimal(extremePoints[2]))
				.stream().map(r -> meepMobilityResourceMapper.meepMobilityResourceEntityToMeepMobilityResource(r))
				.collect(Collectors.toList());
	}

}
