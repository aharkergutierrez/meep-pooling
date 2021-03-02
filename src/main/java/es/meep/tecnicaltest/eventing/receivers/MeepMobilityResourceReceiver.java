package es.meep.tecnicaltest.eventing.receivers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import es.meep.tecnicaltest.database.services.IAccessDataMeepMobilityResourceService;
import es.meep.tecnicaltest.eventing.events.MeepMobilityResourceEvent;
import lombok.extern.slf4j.Slf4j;

@Service
//@RefreshScope
@Slf4j
public class MeepMobilityResourceReceiver{

	public MeepMobilityResourceReceiver() {
		super();
	}
	
	@Autowired
	private IAccessDataMeepMobilityResourceService accessDataMeepMobilityResourceService;
	
	@Async
	@EventListener
	public void onApplicationEvent(MeepMobilityResourceEvent event) {
		accessDataMeepMobilityResourceService.saveMeepMobilityResourceInBD(event.getModelObject());
	}

}
