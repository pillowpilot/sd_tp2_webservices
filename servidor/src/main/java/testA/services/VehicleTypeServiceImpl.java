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

import testA.VehicleTypeService;
import testA.dataAccessObjects.VehicleTypeDAO;
import testA.entities.ResponseMessage;
import testA.entities.VehicleType;

@Path("/vehicleType")
public class VehicleTypeServiceImpl implements VehicleTypeService {
	private VehicleTypeDAO vehicleTypeDao;

	public VehicleTypeServiceImpl(VehicleTypeDAO vehicleTypeDao) {
		this.vehicleTypeDao = vehicleTypeDao;
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addVehicleType(@FormParam("description") String description) {
		if (description == null) {
			String errorMessage = "Description not provided.";
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setMessage(errorMessage);
			return Response.status(400).entity(responseMessage).build();
		}

		VehicleType vehicleType = vehicleTypeDao.create(description);
		return Response.status(200).entity(vehicleType).build();
	}

	@GET
	@Path("/{id}/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteVehicleType(@PathParam("id") Long id) {
		VehicleType vehicleType = vehicleTypeDao.findById(id);
		if (vehicleType == null) {
			String errorMessage = String.format("Vehicle Type with id %d could not be found.", id);
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setMessage(errorMessage);
			return Response.status(400).entity(responseMessage).build();
		} else {
			vehicleTypeDao.delete(vehicleType);
			String successMessage = String.format("Vehicle Type with id %d eliminated.", id);
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setMessage(successMessage);
			return Response.status(200).entity(responseMessage).build();
		}
	}

	@GET
	@Path("/{id}/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVehicleType(@PathParam("id") Long id) {
		VehicleType vehicleType = vehicleTypeDao.findById(id);
		if (vehicleType == null) {
			String errorMessage = String.format("Vehicle Type with id %d could not be found.", id);
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setMessage(errorMessage);
			return Response.status(400).entity(responseMessage).build();
		} else {
			return Response.status(200).entity(vehicleType).build();
		}
	}

	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<VehicleType> getAll() {
		return vehicleTypeDao.getAll();
	}

}
