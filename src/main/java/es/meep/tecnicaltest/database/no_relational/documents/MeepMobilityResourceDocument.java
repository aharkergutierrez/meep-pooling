package es.meep.tecnicaltest.database.no_relational.documents;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class MeepMobilityResourceDocument{

	@Id
	private String id;
	
	private String externalId;
	
	private String name;
	
	private Double longitude;
	
	private Double latitude;
	
	private String licencePlate;
	
	private Double range;
	
	private Double batteryLevel;
	
	private Double helmets;
	
	private String model;
	
	private String resourceImageId;
	
	private Boolean realTimeData;
	
	private String resourceType;
	
	private Double companyZoneId;

}
