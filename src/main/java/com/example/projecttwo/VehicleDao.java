//Name: Michael Felix
//Date: November 17, 2018
//EGR327-A - Software Construction
//Email: Michael.Felix@calbaptist.edu
//Project 2

package com.example.projecttwo;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class VehicleDao {

    @PersistenceContext
    private EntityManager entityManager;
    public void create(Vehicle v) {
        entityManager.persist(v);
    }
    public Vehicle getById(int id) {
        return entityManager.find(Vehicle.class, id);
    }
    public void update(Vehicle vehicle) {
        entityManager.merge(vehicle);
    }
    public void delete(int id) {
        Vehicle toDelete = entityManager.find(Vehicle.class, id);
        if (entityManager.contains(toDelete)) {
            entityManager.remove(toDelete);
        }
    }
    public List<Vehicle> getVehicleList() {
        List <Vehicle> retrievedList = entityManager.createNativeQuery(
                "select * from vehicles ORDER BY id DESC LIMIT 10",
                Vehicle.class).getResultList();
        return retrievedList;
    }
}
