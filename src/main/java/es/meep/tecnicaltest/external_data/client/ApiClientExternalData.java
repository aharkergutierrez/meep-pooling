package es.meep.tecnicaltest.external_data.client;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import es.meep.tecnicaltest.config.properties.PoolingConfigurationDTO;
import es.meep.tecnicaltest.config.properties.PoolingConfigurations;
import es.meep.tecnicaltest.external_data.dto.response.ResponseExternalPoolingDTO;
import es.meep.tecnicaltest.model.MeepMobilityResource;
import es.meep.tecnicaltest.model.mappers.MeepMobilityResourceMapper;
import es.meep.tecnicaltest.utils.UrlUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApiClientExternalData {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private PoolingConfigurations poolingConfigurations;

	@Autowired
	private MeepMobilityResourceMapper meepMobilityResourceMapper;

	public List<MeepMobilityResource> getResorcesFromParametrizedZones() {
		// Se transforma al modelo interno del servicio con el fin de tener un modelo de
		// logica unico aislado de sistemas externos, esto nos permite ser flexibles si
		// cambia el sistema externo y dicho cambio no afecta a nhuestra logica interna,
		// pues solo cambian los clientes del sistema externo y nada mas en el
		// microservicio, por ejemplo un cambio en un nombre de un campo o campos que se
		// retiren o agreguen y que no afecten a nuestra logica, bastara con cambiar los
		// mapeos y el cliente y el resto del microservicio seguira igual
		return poolingConfigurations.getConfigurationZones().stream()
				.map(configurationZone -> Arrays.asList(restTemplate
						.getForEntity(makeUrlFromProperties(configurationZone), ResponseExternalPoolingDTO[].class)
						.getBody()))
				.flatMap(list -> list.stream()).collect(Collectors.toList()).stream()
				.map(item -> meepMobilityResourceMapper.responseExternalPoolingDTOToMeepMobilityResource(item))
				.collect(Collectors.toList());
	}

	private String makeUrlFromProperties(PoolingConfigurationDTO configurationZone) {
		String urlReturnValue = poolingConfigurations.getEndPointUrl();
		List<String> variablesInUrl = UrlUtils.findUrlVariables(urlReturnValue);
		urlReturnValue = UrlUtils.replaceValues(urlReturnValue, new Gson().toJson(configurationZone), variablesInUrl);
		return urlReturnValue;
	}

}
