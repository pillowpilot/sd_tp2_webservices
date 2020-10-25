package testA;

import java.util.List;

import javax.ws.rs.core.Response;

import testA.entities.Vehicle;

public interface VehicleService {
	Response addVehicle(String vehicleTypeDescription);

	Response deleteVehicle(Long id);

	Response getVehicle(Long id);

	List<Vehicle> getAll();
}
