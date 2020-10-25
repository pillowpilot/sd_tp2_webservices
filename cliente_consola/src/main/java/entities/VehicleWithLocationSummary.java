package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VehicleWithLocationSummary {
    public Vehicle vehicle;
    public List<LocationEntry> locations = new ArrayList<>();

    public VehicleWithLocationSummary() {
    }

    public VehicleWithLocationSummary(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void addLocationEntry(Location location, LocalDateTime timestamp) {
        locations.add(new LocationEntry(location, timestamp));
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public List<LocationEntry> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationEntry> locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return "VehicleWithLocationSummary [vehicle=" + vehicle + ", locations=" + locations + "]";
    }

}

