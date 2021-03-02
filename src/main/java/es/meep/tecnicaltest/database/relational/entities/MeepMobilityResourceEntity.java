package es.meep.tecnicaltest.database.relational.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "MEEP_MOBILITY_RESOURCE")
public class MeepMobilityResourceEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6768493471941452276L;
	
	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	@GeneratedValue(generator = "idSequence", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "idSequence", sequenceName = "P_REC_ID_SEQUENCE")
	private Integer id;
	
	@Column(name = "EXTERNAL_ID")
	private String externalId;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "LONGITUDE")
	private BigDecimal longitude;
	
	@Column(name = "LATITUDE")
	private BigDecimal latitude;
	
	@Column(name = "LICENCE_PLATE")
	private String licencePlate;
	
	@Column(name = "RANGE_VALUE")
	private BigDecimal range;
	
	@Column(name = "BATTERY_LEVEL")
	private BigDecimal batteryLevel;
	
	@Column(name = "HELMETS")
	private BigDecimal helmets;
	
	@Column(name = "MODEL")
	private String model;
	
	@Column(name = "RESOURCE_IMAGE_ID")
	private String resourceImageId;
	
	@Column(name = "REAL_TIME_DATA")
	private Boolean realTimeData;
	
	@Column(name = "RESOURCE_TYPE")
	private String resourceType;
	
	@Column(name = "COMPANY_ZONE_ID")
	private BigDecimal companyZoneId;

}
