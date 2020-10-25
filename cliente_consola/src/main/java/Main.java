import entities.LocationBeat;
import entities.Vehicle;
import entities.VehicleType;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

public class Main {
    private static VehicleTypeServices vehicleTypeServices;
    private static VehicleServices vehicleServices;
    private static LocationBeatServices locationBeatServices;

    public static void main(String[] args) throws UnsupportedEncodingException {
        final String javaVersion = System.getProperty("java.version");
        System.out.println("Hello, running on Java " + javaVersion + ".");

        final String hostURL = "http://127.0.0.1:8080/testA-0.0.1-SNAPSHOT";
        vehicleTypeServices = new VehicleTypeServices(hostURL);
        vehicleServices = new VehicleServices(hostURL);
        locationBeatServices = new LocationBeatServices(hostURL);

        /*
        Presentando servicios de vehicle type
         */
        System.out.println("======================== Vehicle Type ========================");

        System.out.println(vehicleTypeServices.getAll());
        System.out.println();
        System.out.println(vehicleTypeServices.getById(1L));
        System.out.println();
        VehicleType vehicleType = vehicleTypeServices.addVehicleType("A NEW VEHICLE TYPE");
        System.out.println(vehicleType);
        System.out.println();
        System.out.println(vehicleTypeServices.getById(vehicleType.getId()));
        System.out.println();
        System.out.println(vehicleTypeServices.deleteById(vehicleType.getId()));

        /*
        Presentado servicios de vehicle
         */
        System.out.println("======================== Vehicle ============================");

        System.out.println(vehicleServices.getAll());
        System.out.println();
        System.out.println(vehicleServices.getById(1L));
        System.out.println();
        VehicleType anotherVehicleType = vehicleTypeServices.addVehicleType("ANOTHER NEW VEHICLE TYPE");
        Vehicle vehicle = vehicleServices.addVehicle(anotherVehicleType.getDescription());
        System.out.println(vehicle);
        System.out.println();
        System.out.println(vehicleServices.getById(vehicle.getId()));
        System.out.println();
        System.out.println(vehicleServices.deleteById(vehicle.getId()));
        System.out.println(vehicleTypeServices.deleteById(anotherVehicleType.getId()));

        /*
        Presentando servicios de location beat
         */
        System.out.println("======================== LocationBeat =========================");

        System.out.println(locationBeatServices.getAll());
        System.out.println();
        System.out.println(locationBeatServices.getById(1L));
        System.out.println();
        LocationBeat locationBeat = locationBeatServices.addLocationBeat(2L, 40.0, 40.0, LocalDateTime.now());
        System.out.println(locationBeat);
        System.out.println(locationBeatServices.getById(locationBeat.getId()));
        System.out.println();
        System.out.println(locationBeatServices.deleteById(locationBeat.getId()));
        System.out.println();
        System.out.println(locationBeatServices.getClosest(-25.2637, -57.57, 10.0));
        System.out.println(locationBeatServices.getClosest(-25.2637, -57.57, 1.0));
        System.out.println(locationBeatServices.getClosest(-25.2637, -57.57, 50.0));
    }
}
