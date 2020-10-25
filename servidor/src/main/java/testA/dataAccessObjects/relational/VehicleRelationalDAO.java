package testA.dataAccessObjects.relational;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.sun.istack.Nullable;

import testA.dataAccessObjects.VehicleDAO;
import testA.entities.Vehicle;
import testA.entities.VehicleType;

public class VehicleRelationalDAO implements VehicleDAO {
	private EntityManager entityManager;

	public VehicleRelationalDAO(EntityManagerFactory entityManagerFactory) {
		this.entityManager = entityManagerFactory.createEntityManager();
	}

	public @Nullable Vehicle findById(Long id) {
		return entityManager.find(Vehicle.class, id);
	}

	// See https://stackoverflow.com/a/38337408/7555119
	public List<Vehicle> getAll() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Vehicle> criteriaQuery = criteriaBuilder.createQuery(Vehicle.class);
		Root<Vehicle> rootEntry = criteriaQuery.from(Vehicle.class);
		CriteriaQuery<Vehicle> all = criteriaQuery.select(rootEntry);
		TypedQuery<Vehicle> allQuery = entityManager.createQuery(all);
		return allQuery.getResultList();
	}

	public Vehicle create(VehicleType vehicleType) {
		entityManager.getTransaction().begin();

		Vehicle newVehicle = new Vehicle();
		newVehicle.setVehicleType(vehicleType);
		entityManager.persist(newVehicle);

		entityManager.getTransaction().commit();

		return newVehicle;
	}

	public void update(Vehicle vehicle) {
		entityManager.getTransaction().begin();

		entityManager.merge(vehicle);

		entityManager.getTransaction().commit();
	}

	public void delete(Vehicle vehicle) {
		entityManager.getTransaction().begin();

		entityManager.remove(vehicle);

		entityManager.getTransaction().commit();
	}

}
