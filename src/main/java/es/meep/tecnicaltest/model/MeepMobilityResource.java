package es.meep.tecnicaltest.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MeepMobilityResource implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6768493471941452276L;
	
	private String ownId;
	private String externalId;
	private String name;
	private BigDecimal longitude;
	private BigDecimal latitude;
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
