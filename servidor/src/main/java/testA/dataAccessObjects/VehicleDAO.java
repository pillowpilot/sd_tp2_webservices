package testA.dataAccessObjects;

import java.util.List;

import com.sun.istack.Nullable;

import testA.entities.Vehicle;
import testA.entities.VehicleType;

public interface VehicleDAO {
	Vehicle create(VehicleType vehicleType);

	@Nullable
	Vehicle findById(Long id);

	List<Vehicle> getAll();

	void update(Vehicle vehicle);

	void delete(Vehicle vehicle);
}
