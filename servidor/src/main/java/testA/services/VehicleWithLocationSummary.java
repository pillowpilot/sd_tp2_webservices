package testA.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.sun.istack.internal.NotNull;

import testA.entities.Location;
import testA.entities.Vehicle;

public class VehicleWithLocationSummary {

	public class LocationEntry {
		Location location;
		LocalDateTime timestamp;

		public LocationEntry(Location location, LocalDateTime timestamp) {
			this.location = location;
			this.timestamp = timestamp;
		}

		public Location getLocation() {
			return location;
		}

		public void setLocation(Location location) {
			this.location = location;
		}

		public LocalDateTime getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(LocalDateTime timestamp) {
			this.timestamp = timestamp;
		}
	}

	public @NotNull Vehicle vehicle;
	public List<LocationEntry> locations = new ArrayList<>();

	public VehicleWithLocationSummary(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public void addLocationEntry(Location location, LocalDateTime timestamp) {
		locations.add(new LocationEntry(location, timestamp));
	}

	@Override
	public String toString() {
		return "VehicleWithLocationSummary [vehicle=" + vehicle + ", locations=" + locations + "]";
	}

}
