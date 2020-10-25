package entities;

import java.time.LocalDateTime;

public class LocationBeat {
    private Long id;
    private Vehicle vehicle;
    private Location location;
    private LocalDateTime createdOn;

    public LocationBeat() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "LocationBeat{" +
                "id=" + id +
                ", vehicle=" + vehicle +
                ", location=" + location +
                ", createdOn=" + createdOn +
                '}';
    }
}
