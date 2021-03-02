package es.meep.tecnicaltest.controller.dto.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NearbyDeviceResponseDTO {

	private String ownId;
	private String externalId;
	private BigDecimal longitude;
	private BigDecimal latitude;
	private BigDecimal companyZoneId;
	
}
