package entities;

public class VehicleType {
    private Long id;
    private String description;

    public VehicleType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "VehicleType{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
