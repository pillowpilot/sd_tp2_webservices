package testA;

import java.util.List;

import javax.ws.rs.core.Response;

import testA.entities.LocationBeat;

public interface LocationBeatService {
	Response addLocationBeat(Long vehicleId, Double latitude, Double longitude, String timestampAsString);

	Response deleteLocationBeat(Long id);

	Response getLocationBeat(Long id);

	Response getClosestInRange(Double latitude, Double longitude, Double distance);

	List<LocationBeat> getAll();
}
