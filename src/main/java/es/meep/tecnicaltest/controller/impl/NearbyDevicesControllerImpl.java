package es.meep.tecnicaltest.controller.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.meep.tecnicaltest.controller.NearbyDevicesController;
import es.meep.tecnicaltest.controller.dto.response.NearbyDeviceResponseDTO;
import es.meep.tecnicaltest.database.services.IAccessDataMeepMobilityResourceService;
import es.meep.tecnicaltest.eventing.kafka.producers.PublisherKafkaEvents;
import es.meep.tecnicaltest.model.MeepMobilityResource;
import es.meep.tecnicaltest.model.mappers.MeepMobilityResourceMapper;

@RestController
@RequestMapping("/nearby-devices")
public class NearbyDevicesControllerImpl implements NearbyDevicesController {

	@Autowired
	private IAccessDataMeepMobilityResourceService accessDataMeepMobilityResourceService;

	@Autowired
	private MeepMobilityResourceMapper meepMobilityResourceMapper;

	@Autowired
	private PublisherKafkaEvents publisherKafkaEvents;

	@Override
	@GetMapping(value = "/from-relational-db/{latitude},{longitude},{distanceInMetersToSquare}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<NearbyDeviceResponseDTO> getNearbyDevices(@PathVariable("latitude") BigDecimal latitude,
			@PathVariable("longitude") BigDecimal longitude,
			@PathVariable("distanceInMetersToSquare") Integer distanceInMetersToSquare) {
		return accessDataMeepMobilityResourceService
				.getResourcesInRange(longitude.doubleValue(), latitude.doubleValue(), distanceInMetersToSquare).stream()
				.map(r -> meepMobilityResourceMapper.meepMobilityResourceEntityToNearbyDeviceResponseDTO(r))
				.collect(Collectors.toList());
	}

	@Override
	@GetMapping(value = "/hello-world", produces = MediaType.TEXT_PLAIN_VALUE)
	public String helloWorld() {
//		publisherKafkaEvents.publishEvent(new MeepMobilityResource("ownId", "externalId", "name", new BigDecimal("1.0"),
//				new BigDecimal("2.0"), "licencePlate", new BigDecimal("3.0"), new BigDecimal("4.0"),
//				new BigDecimal("5.0"), "model", "resourceImageId", true, "resourceType", new BigDecimal("6.0")));
		return "HELLO";
	}

}
