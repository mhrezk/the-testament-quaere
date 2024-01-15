package com.testament.veltahleon.repositories.dao.implementation.places;

import com.testament.veltahleon.model.entities.places.Province;
import com.testament.veltahleon.repositories.dao.ifc.places.ProvinceDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class ProvinceDAOImpl implements ProvinceDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Province> getProvinces() {
        TypedQuery<Province> query = entityManager.createQuery("FROM Province", Province.class);
        return query.getResultList();
    }

    @Override
    public Province getProvinceByID(Long id) {
        return entityManager.find(Province.class, id);
    }

    @Override
    @Transactional
    public Boolean deleteProvinceByID(Long id) {
        Province province = entityManager.find(Province.class, id);
        entityManager.remove(province);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Province saveProvince(Province province) {
        entityManager.persist(province);
        return province;
    }

    @Override
    @Transactional
    public Province updateProvince(Province province) {
        return entityManager.merge(province);
    }
}
