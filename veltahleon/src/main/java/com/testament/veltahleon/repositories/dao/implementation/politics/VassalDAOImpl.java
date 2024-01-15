package com.testament.veltahleon.repositories.dao.implementation.politics;

import com.testament.veltahleon.model.entities.politics.Vassal;
import com.testament.veltahleon.repositories.dao.ifc.politics.VassalDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class VassalDAOImpl implements VassalDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Vassal> getVassals() {
        TypedQuery<Vassal> query = entityManager.createQuery("FROM Vassal", Vassal.class);
        return query.getResultList();
    }

    @Override
    public Vassal getVassalByID(Long id) {
        return entityManager.find(Vassal.class, id);
    }

    @Override
    @Transactional
    public Boolean deleteVassalByID(Long id) {
        Vassal vassal = entityManager.find(Vassal.class, id);
        entityManager.remove(vassal);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Vassal saveVassal(Vassal vassal) {
        entityManager.persist(vassal);
        return vassal;
    }

    @Override
    @Transactional
    public Vassal updateVassal(Vassal vassal) {
        return entityManager.merge(vassal);
    }
}
