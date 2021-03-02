package es.meep.tecnicaltest.config.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Configuration
@ConfigurationProperties(prefix = "meep.pooling.configuration")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class PoolingConfigurations {
	private String endPointUrl;
	private List<PoolingConfigurationDTO> configurationZones; 
}
