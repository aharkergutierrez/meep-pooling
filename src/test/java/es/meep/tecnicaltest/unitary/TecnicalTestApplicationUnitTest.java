package es.meep.tecnicaltest.unitary;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.diff.JsonDiff;
import com.google.gson.Gson;

import es.meep.tecnicaltest.controller.dto.response.NearbyDeviceResponseDTO;
import es.meep.tecnicaltest.controller.impl.NearbyDevicesControllerImpl;
import es.meep.tecnicaltest.database.services.IAccessDataMeepMobilityResourceService;
import es.meep.tecnicaltest.model.MeepMobilityResource;
import es.meep.tecnicaltest.model.mappers.MeepMobilityResourceMapper;
import es.meep.tecnicaltest.model.mappers.MeepMobilityResourceMapperImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TecnicalTestApplicationUnitTest {

	@InjectMocks
	private NearbyDevicesControllerImpl nearbyDevicesController;

	@Mock
	private IAccessDataMeepMobilityResourceService accessDataMeepMobilityResourceService;

	@Mock
	private MeepMobilityResourceMapperImpl meepMobilityResourceMapper;

//	private MeepMobilityResourceMapper meepMobilityResourceMapperInstance = new MeepMobilityResourceMapperImpl();

	@Test
	public void testGetNearbyDevices() throws JsonMappingException, JsonProcessingException {

		List<MeepMobilityResource> returnValue = new ArrayList<MeepMobilityResource>();

		MeepMobilityResource meepMobilityResource = new MeepMobilityResource("ownId", "externalId", "name",
				new BigDecimal("1.0"), new BigDecimal("2.0"), "licencePlate", new BigDecimal("3.0"),
				new BigDecimal("4.0"), new BigDecimal("5.0"), "model", "resourceImageId", true, "resourceType",
				new BigDecimal("6.0"));

		returnValue.add(meepMobilityResource);

		when(accessDataMeepMobilityResourceService.getResourcesInRange(any(), any(), any())).thenReturn(returnValue);

		when(meepMobilityResourceMapper.meepMobilityResourceEntityToNearbyDeviceResponseDTO(any()))
				.thenCallRealMethod();

		// Test
		List<NearbyDeviceResponseDTO> nearbyDeviceResponseDTOList = nearbyDevicesController
				.getNearbyDevices(new BigDecimal("1.0"), new BigDecimal("2.0"), 1000);

		ObjectMapper jackson = new ObjectMapper();
		String returnValueInJson = (new Gson()).toJson(nearbyDeviceResponseDTOList);
		String requestValueInJson = "[{\"ownId\":\"ownId\",\"externalId\":\"externalId\",\"longitude\":1.0,\"latitude\":2.0,\"companyZoneId\":6.0}]";

		JsonNode beforeNode = jackson.readTree(requestValueInJson);
		JsonNode afterNode = jackson.readTree(returnValueInJson);
		JsonNode patchNode = JsonDiff.asJson(beforeNode, afterNode);
		String diff = patchNode.toString();

		assertEquals(diff, "[]");
	}

}
