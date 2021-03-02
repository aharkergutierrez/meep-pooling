package es.meep.tecnicaltest.external_data.dto.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseExternalPoolingDTO {

	private String id;
	private String name;
	private BigDecimal x;
	private BigDecimal y;
	private String licencePlate;
	private BigDecimal range;
	private BigDecimal batteryLevel;
	private BigDecimal helmets;
	private String model;
	private String resourceImageId;
	private Boolean realTimeData;
	private String resourceType;
	private BigDecimal companyZoneId;

}
