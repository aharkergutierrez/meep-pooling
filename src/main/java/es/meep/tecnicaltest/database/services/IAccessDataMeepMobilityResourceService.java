package es.meep.tecnicaltest.database.services;

import java.util.List;

import es.meep.tecnicaltest.model.MeepMobilityResource;

public abstract class IAccessDataMeepMobilityResourceService {

	public abstract MeepMobilityResource saveMeepMobilityResourceInBD(MeepMobilityResource meepMobilityResource);

	public abstract List<MeepMobilityResource> getResourcesInRange(Double longitude, Double latitude, Integer meters);
	
	protected Double[] calculePositions(Double lat1, Double lon1, Double metersX, Double metersY) { // generally used geo
		// measurement
		// function
		Double radiousTerran = 6378.0; // Radius of earth in KM

		Double[] returnValue = new Double[4];// valores minimos primero y maximos despues

		returnValue[0] = lat1 - ((metersY / 1000D) / radiousTerran) * (180 / Math.PI);
		returnValue[1] = lon1
				- (((metersY / 1000D)) / radiousTerran) * (180 / Math.PI) / Math.cos(lat1 * Math.PI / 180);

		returnValue[2] = lat1 + ((metersY / 1000D) / radiousTerran) * (180 / Math.PI);
		returnValue[3] = lon1
				+ (((metersY / 1000D)) / radiousTerran) * (180 / Math.PI) / Math.cos(lat1 * Math.PI / 180);

		return returnValue; // meters
	}

}
