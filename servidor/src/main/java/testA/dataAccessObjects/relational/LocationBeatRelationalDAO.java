package testA.dataAccessObjects.relational;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.sun.istack.Nullable;

import testA.dataAccessObjects.LocationBeatDAO;
import testA.entities.Location;
import testA.entities.LocationBeat;
import testA.entities.Vehicle;

public class LocationBeatRelationalDAO implements LocationBeatDAO {
	private EntityManager entityManager;

	public LocationBeatRelationalDAO(EntityManagerFactory entityManagerFactory) {
		this.entityManager = entityManagerFactory.createEntityManager();
	}

	public LocationBeat create(Vehicle vehicle, Location location, LocalDateTime createdOn) {
		entityManager.getTransaction().begin();

		LocationBeat newLocationBeat = new LocationBeat();
		newLocationBeat.setVehicle(vehicle);
		newLocationBeat.setLocation(location);
		newLocationBeat.setCreatedOn(createdOn);
		entityManager.persist(newLocationBeat);

		entityManager.getTransaction().commit();

		return newLocationBeat;
	}
	
	public @Nullable LocationBeat findById(Long id) {
		return entityManager.find(LocationBeat.class, id);
	}

	public List<LocationBeat> getAll() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<LocationBeat> criteriaQuery = criteriaBuilder.createQuery(LocationBeat.class);
		Root<LocationBeat> rootEntry = criteriaQuery.from(LocationBeat.class);
		CriteriaQuery<LocationBeat> all = criteriaQuery.select(rootEntry);
		TypedQuery<LocationBeat> allQuery = entityManager.createQuery(all);
		return allQuery.getResultList();
	}

	public void update(LocationBeat locationBeat) {
		entityManager.getTransaction().begin();

		entityManager.merge(locationBeat);

		entityManager.getTransaction().commit();
	}

	public void delete(LocationBeat locationBeat) {
		entityManager.getTransaction().begin();

		entityManager.remove(locationBeat);

		entityManager.getTransaction().commit();
	}

}
