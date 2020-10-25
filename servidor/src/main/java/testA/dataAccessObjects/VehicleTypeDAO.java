package testA.dataAccessObjects;

import java.util.List;

import com.sun.istack.Nullable;

import testA.entities.VehicleType;

public interface VehicleTypeDAO {
	VehicleType create(String description);

	@Nullable
	VehicleType findById(Long id);

	@Nullable
	VehicleType findByDescription(String description);

	List<VehicleType> getAll();

	void update(VehicleType vehicleType);

	void delete(VehicleType vehicleType);
}
