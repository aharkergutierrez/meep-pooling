package es.meep.tecnicaltest.config.properties;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoolingConfigurationDTO {
	private MapPointDTO lowerLeft;
	private MapPointDTO upperRight;
	private List<Integer> companyZoneIds; 
	private String country;
}
