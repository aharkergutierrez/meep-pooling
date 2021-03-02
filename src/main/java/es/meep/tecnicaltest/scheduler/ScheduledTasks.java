package es.meep.tecnicaltest.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import es.meep.tecnicaltest.eventing.PublisherEvents;
import es.meep.tecnicaltest.eventing.events.MeepMobilityResourceEvent;
import es.meep.tecnicaltest.external_data.client.ApiClientExternalData;
import es.meep.tecnicaltest.model.MeepMobilityResource;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ScheduledTasks {
	@Autowired
	private ApiClientExternalData apiClientExternalData;

	@Autowired
	private PublisherEvents publisherEvents;
	
	@Scheduled(cron = "${meep.cron.expression}")
	public void poolingDevices() {
		List<MeepMobilityResource> responseEntities = apiClientExternalData.getResorcesFromParametrizedZones();
		publisherEvents.publishEventsFromModelClass(responseEntities, MeepMobilityResourceEvent.class, this);
	}

}
