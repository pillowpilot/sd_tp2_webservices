package testA.dataAccessObjects.relational;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.sun.istack.Nullable;

import testA.dataAccessObjects.VehicleTypeDAO;
import testA.entities.VehicleType;

public class VehicleTypeRelationalDAO implements VehicleTypeDAO {
	private EntityManager entityManager;

	public VehicleTypeRelationalDAO(EntityManagerFactory entityManagerFactory) {
		this.entityManager = entityManagerFactory.createEntityManager();
	}

	public VehicleType create(String description) {
		entityManager.getTransaction().begin();

		VehicleType newVehicleType = new VehicleType();
		newVehicleType.setDescription(description);
		entityManager.persist(newVehicleType);

		entityManager.getTransaction().commit();

		return newVehicleType;
	}

	public @Nullable VehicleType findById(Long id) {
		return entityManager.find(VehicleType.class, id);
	}

	// See https://stackoverflow.com/a/59361897/7555119
	public @Nullable VehicleType findByDescription(String description) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<VehicleType> criteriaQuery = criteriaBuilder.createQuery(VehicleType.class);
		Root<VehicleType> rootEntry = criteriaQuery.from(VehicleType.class);
		criteriaQuery.select(rootEntry).where(criteriaBuilder.equal(rootEntry.get("description"), description));

		TypedQuery<VehicleType> query = entityManager.createQuery(criteriaQuery);
		query.setMaxResults(1);

		List<VehicleType> results = query.getResultList();
		if (results.isEmpty())
			return null;
		else
			return results.get(0);
	}

	// See https://stackoverflow.com/a/38337408/7555119
	public List<VehicleType> getAll() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<VehicleType> criteriaQuery = criteriaBuilder.createQuery(VehicleType.class);
		Root<VehicleType> rootEntry = criteriaQuery.from(VehicleType.class);
		CriteriaQuery<VehicleType> all = criteriaQuery.select(rootEntry);
		TypedQuery<VehicleType> allQuery = entityManager.createQuery(all);
		return allQuery.getResultList();
	}

	public void update(VehicleType vehicleType) {
		entityManager.getTransaction().begin();

		entityManager.merge(vehicleType);

		entityManager.getTransaction().commit();
	}

	public void delete(VehicleType vehicleType) {
		entityManager.getTransaction().begin();

		entityManager.remove(vehicleType);

		entityManager.getTransaction().commit();
	}

}
