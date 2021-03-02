package es.meep.tecnicaltest.eventing.events;

import es.meep.tecnicaltest.model.MeepMobilityResource;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SelectorEventsFromModelObjectEnum {

	MEEP_MOVILITY_RESOURCE(MeepMobilityResource.class, MeepMobilityResourceEvent.class);
	
	private Class modelClass;
	private Class eventClass;
	
}
