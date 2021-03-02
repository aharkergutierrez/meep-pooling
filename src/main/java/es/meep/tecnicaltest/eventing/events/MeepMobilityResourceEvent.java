package es.meep.tecnicaltest.eventing.events;

import es.meep.tecnicaltest.model.MeepMobilityResource;
import lombok.Data;

@Data
public class MeepMobilityResourceEvent extends GenericMeepEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2273266199509800015L;
	
	private MeepMobilityResource modelObject;
	
	public MeepMobilityResourceEvent(Object source) {
		super(source);
	}

}
