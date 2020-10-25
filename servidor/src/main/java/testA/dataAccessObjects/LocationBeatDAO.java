package testA.dataAccessObjects;

import java.time.LocalDateTime;
import java.util.List;

import com.sun.istack.Nullable;

import testA.entities.Location;
import testA.entities.LocationBeat;
import testA.entities.Vehicle;

public interface LocationBeatDAO {
	LocationBeat create(Vehicle vehicle, Location location, LocalDateTime createdOn);

	@Nullable
	LocationBeat findById(Long id);

	List<LocationBeat> getAll();

	void update(LocationBeat locationBeat);

	void delete(LocationBeat locationBeat);
}
