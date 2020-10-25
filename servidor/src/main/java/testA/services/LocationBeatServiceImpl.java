package testA.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import testA.LocationBeatService;
import testA.dataAccessObjects.LocationBeatDAO;
import testA.dataAccessObjects.VehicleDAO;
import testA.entities.Location;
import testA.entities.LocationBeat;
import testA.entities.ResponseMessage;
import testA.entities.Vehicle;

@Path("/locationbeat")
public class LocationBeatServiceImpl implements LocationBeatService {
	private VehicleDAO vehicleDao;
	private LocationBeatDAO locationBeatDao;

	public LocationBeatServiceImpl(VehicleDAO vehicleDao, LocationBeatDAO locationBeatDao) {
		this.vehicleDao = vehicleDao;
		this.locationBeatDao = locationBeatDao;
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addLocationBeat(@FormParam("vehicleId") Long vehicleId, @FormParam("latitude") Double latitude,
			@FormParam("longitude") Double longitude,
			@FormParam("timestamp") @DefaultValue("useCurrentTime") String timestampAsString) {

		try {

			LocalDateTime timestamp;
			if (timestampAsString.equals("useCurrentTime")) {
				timestamp = LocalDateTime.now();
			} else {
				// Throws DateTimeParseException if timestampAsString is not parseable.
				timestamp = LocalDateTime.parse(timestampAsString);
			}

			Location location = new Location();
			location.setLatitude(latitude);
			location.setLongitude(longitude);

			Vehicle vehicle = vehicleDao.findById(vehicleId);

			if (vehicle == null) {
				String errorMessage = String.format("Vehicle with id %d cannot be found.", vehicleId);
				ResponseMessage responseMessage = new ResponseMessage();
				responseMessage.setMessage(errorMessage);
				return Response.status(400).entity(responseMessage).build();
			} else {
				LocationBeat locationBeat = locationBeatDao.create(vehicle, location, timestamp);
				return Response.status(200).entity(locationBeat).build();
			}

		} catch (DateTimeParseException ex) {
			String errorMessage = String.format("Timestamp %s cannot be parsed. Use ISO 8601.", timestampAsString);
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setMessage(errorMessage);
			return Response.status(400).entity(responseMessage).build();
		}
	}

	@GET
	@Path("/{id}/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteLocationBeat(@PathParam("id") Long id) {
		LocationBeat locationBeat = locationBeatDao.findById(id);
		if (locationBeat == null) {
			String errorMessage = String.format("LocationBeat with id %d could not be found.", id);
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setMessage(errorMessage);
			return Response.status(400).entity(responseMessage).build();
		} else {
			locationBeatDao.delete(locationBeat);
			String successMessage = String.format("LocationBeat with id %d eliminated.", id);
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setMessage(successMessage);
			return Response.status(200).entity(responseMessage).build();
		}
	}

	@GET
	@Path("/{id}/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLocationBeat(@PathParam("id") Long id) {
		LocationBeat locationBeat = locationBeatDao.findById(id);
		if (locationBeat == null) {
			String errorMessage = String.format("LocationBeat with id %d could not be found.", id);
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setMessage(errorMessage);
			return Response.status(400).entity(responseMessage).build();
		} else {
			return Response.status(200).entity(locationBeat).build();
		}
	}

	@GET
	@Path("/getClosest/{latitude}/{longitude}/{distance}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getClosestInRange(@PathParam("latitude") Double latitude, @PathParam("longitude") Double longitude,
			@PathParam("distance") Double distance) {

		if (distance < 0.0) {
			String errorMessage = String.format("Maximum distance %f can not be negative.", distance);
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setMessage(errorMessage);
			return Response.status(400).entity(responseMessage).build();
		}

		List<LocationBeat> allLocationBeats = locationBeatDao.getAll();

		Map<Long, VehicleWithLocationSummary> result = new HashMap<>();

		List<LocationBeat> locationBeatsInRange = new ArrayList<>();
		for (LocationBeat l : allLocationBeats) {
			Double currentDistance = Haversine.distance(latitude, longitude, l.getLocation().getLatitude(),
					l.getLocation().getLongitude());
			if (currentDistance <= distance) {
				locationBeatsInRange.add(l);

				Long vehicleId = l.getVehicle().getId();
				if (!result.containsKey(vehicleId)) {
					result.put(vehicleId, new VehicleWithLocationSummary(l.getVehicle()));
				}
				result.get(vehicleId).addLocationEntry(l.getLocation(), l.getCreatedOn());
			}
		}
		
		List<VehicleWithLocationSummary> results = new ArrayList<>();
		for(VehicleWithLocationSummary e: result.values())
			results.add(e);

		return Response.status(200).entity(results).build();
	}

	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<LocationBeat> getAll() {
		return locationBeatDao.getAll();
	}

}
