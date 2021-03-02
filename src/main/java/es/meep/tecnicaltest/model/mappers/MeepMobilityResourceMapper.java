package es.meep.tecnicaltest.model.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.meep.tecnicaltest.controller.dto.response.NearbyDeviceResponseDTO;
import es.meep.tecnicaltest.database.no_relational.documents.MeepMobilityResourceDocument;
import es.meep.tecnicaltest.database.relational.entities.MeepMobilityResourceEntity;
import es.meep.tecnicaltest.external_data.dto.response.ResponseExternalPoolingDTO;
import es.meep.tecnicaltest.model.MeepMobilityResource;

@Mapper(componentModel = "spring")
public interface MeepMobilityResourceMapper {

	@Mapping(target = "externalId", source = "id")
	@Mapping(target = "latitude", source = "x")
	@Mapping(target = "longitude", source = "y")
	MeepMobilityResource responseExternalPoolingDTOToMeepMobilityResource(ResponseExternalPoolingDTO source);
	
	@Mapping(target = "id", source = "ownId")
	MeepMobilityResourceEntity meepMobilityResourceToMeepMobilityResourceEntity(MeepMobilityResource source);
	
	@Mapping(target = "ownId", source = "id")
	MeepMobilityResource meepMobilityResourceEntityToMeepMobilityResource(MeepMobilityResourceEntity source);

	NearbyDeviceResponseDTO meepMobilityResourceEntityToNearbyDeviceResponseDTO(MeepMobilityResource source);

	@Mapping(target = "id", source = "ownId")
	MeepMobilityResourceDocument meepMobilityResourceToMeepMobilityResourceDocument(MeepMobilityResource meepMobilityResource);

	@Mapping(target = "ownId", source = "id")
	MeepMobilityResource meepMobilityResourceEntityToMeepMobilityResource(MeepMobilityResourceDocument save);
	
	
}
