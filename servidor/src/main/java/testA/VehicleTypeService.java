package testA;

import java.util.List;

import javax.ws.rs.core.Response;

import testA.entities.VehicleType;

public interface VehicleTypeService {
	Response addVehicleType(String description);

	Response deleteVehicleType(Long id);

	Response getVehicleType(Long id);

	List<VehicleType> getAll();
}
