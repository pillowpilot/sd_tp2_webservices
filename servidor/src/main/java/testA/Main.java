package testA;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Application;

import testA.dataAccessObjects.LocationBeatDAO;
import testA.dataAccessObjects.VehicleDAO;
import testA.dataAccessObjects.VehicleTypeDAO;
import testA.dataAccessObjects.relational.LocationBeatRelationalDAO;
import testA.dataAccessObjects.relational.VehicleRelationalDAO;
import testA.dataAccessObjects.relational.VehicleTypeRelationalDAO;
import testA.services.LocationBeatServiceImpl;
import testA.services.VehicleServiceImpl;
import testA.services.VehicleTypeServiceImpl;

public class Main extends Application {
	private Set<Object> singletons = new HashSet<Object>();

	public Main() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("HypersistenceOptimizer");

		VehicleTypeDAO vehicleTypeDao = new VehicleTypeRelationalDAO(factory);
		VehicleTypeService vehicleTypeService = new VehicleTypeServiceImpl(vehicleTypeDao);
		singletons.add(vehicleTypeService);

		VehicleDAO vehicleDao = new VehicleRelationalDAO(factory);
		VehicleService vehicleService = new VehicleServiceImpl(vehicleDao, vehicleTypeDao);
		singletons.add(vehicleService);

		LocationBeatDAO locationBeatDao = new LocationBeatRelationalDAO(factory);
		LocationBeatService locationBeatService = new LocationBeatServiceImpl(vehicleDao, locationBeatDao);
		singletons.add(locationBeatService);
	}

	public Set<Object> getSingletons() {
		return singletons;
	}

	public static void main(String[] args) {

	}

}
