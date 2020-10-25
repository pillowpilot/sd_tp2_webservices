package entities;

import java.time.LocalDateTime;

public class LocationEntry {
    private Location location;
    private LocalDateTime timestamp;

    public LocationEntry() {
    }

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

    @Override
    public String toString() {
        return "LocationEntry{" +
                "location=" + location +
                ", timestamp=" + timestamp +
                '}';
    }
}
