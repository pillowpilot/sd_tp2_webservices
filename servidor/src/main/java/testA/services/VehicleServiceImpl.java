package testA.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import testA.VehicleService;
import testA.dataAccessObjects.VehicleDAO;
import testA.dataAccessObjects.VehicleTypeDAO;
import testA.entities.ResponseMessage;
import testA.entities.Vehicle;
import testA.entities.VehicleType;

@Path("/vehicle")
public class VehicleServiceImpl implements VehicleService {
	private VehicleDAO vehicleDao;
	private VehicleTypeDAO vehicleTypeDao;

	public VehicleServiceImpl(VehicleDAO vehicleDao, VehicleTypeDAO vehicleTypeDao) {
		this.vehicleDao = vehicleDao;
		this.vehicleTypeDao = vehicleTypeDao;
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addVehicle(@FormParam("vehicleTypeDescription") String vehicleTypeDescription) {
		if (vehicleTypeDescription == null) {
			String errorMessage = "Vehicle Type Description not provided.";
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setMessage(errorMessage);
			return Response.status(400).entity(responseMessage).build();
		}

		VehicleType vehicleType = vehicleTypeDao.findByDescription(vehicleTypeDescription);
		if (vehicleType == null) {
			String errorMessage = "Vehicle Type Description do not exists.";
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setMessage(errorMessage);
			return Response.status(400).entity(responseMessage).build();
		}

		Vehicle vehicle = vehicleDao.create(vehicleType);
		return Response.status(200).entity(vehicle).build();
	}

	@GET
	@Path("/{id}/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteVehicle(@PathParam("id") Long id) {
		Vehicle vehicle = vehicleDao.findById(id);
		if (vehicle == null) {
			String errorMessage = String.format("Vehicle with id %d could not be found.", id);
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setMessage(errorMessage);
			return Response.status(400).entity(responseMessage).build();
		} else {
			vehicleDao.delete(vehicle);
			String successMessage = String.format("Vehicle with id %d eliminated.", id);
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setMessage(successMessage);
			return Response.status(200).entity(responseMessage).build();
		}
	}

	@GET
	@Path("/{id}/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVehicle(@PathParam("id") Long id) {
		Vehicle vehicle = vehicleDao.findById(id);
		if (vehicle == null) {
			String errorMessage = String.format("Vehicle with id %d could not be found.", id);
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setMessage(errorMessage);
			return Response.status(400).entity(responseMessage).build();
		} else {
			return Response.status(200).entity(vehicle).build();
		}

	}

	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Vehicle> getAll() {
		return vehicleDao.getAll();
	}

}
