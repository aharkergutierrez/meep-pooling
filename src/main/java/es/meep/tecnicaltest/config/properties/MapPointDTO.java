package es.meep.tecnicaltest.config.properties;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapPointDTO {

	private BigDecimal latitude;
	private BigDecimal longitude;
	
}
