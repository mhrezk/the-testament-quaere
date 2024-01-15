package com.testament.veltahleon.repositories.dao.implementation.places;

import com.testament.veltahleon.model.entities.places.Location;
import com.testament.veltahleon.repositories.dao.ifc.places.LocationDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class LocationDAOImpl implements LocationDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Location> getLocations() {
        TypedQuery<Location> query = entityManager.createQuery("FROM Location", Location.class);
        return query.getResultList();
    }

    @Override
    public Location getLocationByID(Long id) {
        return entityManager.find(Location.class, id);
    }

    @Override
    @Transactional
    public Boolean deleteLocationByID(Long id) {
        Location location = entityManager.find(Location.class, id);
        entityManager.remove(location);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Location saveLocation(Location location) {
        entityManager.persist(location);
        return location;
    }

    @Override
    @Transactional
    public Location updateLocation(Location location) {
        return entityManager.merge(location);
    }
}
