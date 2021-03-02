package es.meep.tecnicaltest.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import es.meep.tecnicaltest.controller.dto.response.GeneralMeepExceptionDTO;
import es.meep.tecnicaltest.controller.dto.response.NearbyDeviceResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "nearby-devices")
public interface NearbyDevicesController {
	
	@ApiOperation(value = "", nickname = "get nearby devices by position", notes = "get nearby devices by position", response = NearbyDeviceResponseDTO.class, tags = {
			"NEARBY_DEVICES" })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "get nearby devices by position success", response = NearbyDeviceResponseDTO.class),
			@ApiResponse(code = 400, message = "get nearby devices by position bad request", response = GeneralMeepExceptionDTO.class),
			@ApiResponse(code = 401, message = "The method is not authorized", response = GeneralMeepExceptionDTO.class),
			@ApiResponse(code = 404, message = "Not found devices", response = GeneralMeepExceptionDTO.class),
			@ApiResponse(code = 500, message = "An error occurred on the server", response = GeneralMeepExceptionDTO.class) })
	List<NearbyDeviceResponseDTO> getNearbyDevices(
			@ApiParam(value = "latitude", required = true) @PathVariable("latitude") BigDecimal latitude,
			@ApiParam(value = "longitude", required = true) @PathVariable("longitude") BigDecimal longitude,
			@ApiParam(value = "distance in meters to square", required = true) @PathVariable("distanceInMetersToSquare") Integer distanceInMetersToSquare);

	@ApiOperation(value = "", nickname = "hello world controller", notes = "hello world controller", response = String.class, tags = {
			"NEARBY_DEVICES" })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "get nearby devices by position success", response = String.class) })
	String helloWorld();

}
