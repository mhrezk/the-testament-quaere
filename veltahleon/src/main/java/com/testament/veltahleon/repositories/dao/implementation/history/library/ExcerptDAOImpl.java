package com.testament.veltahleon.repositories.dao.implementation.history.library;

import com.testament.veltahleon.model.entities.history.library.Excerpt;
import com.testament.veltahleon.repositories.dao.ifc.history.library.ExcerptDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class ExcerptDAOImpl implements ExcerptDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Excerpt> getExcerpts() {
        TypedQuery<Excerpt> query = entityManager.createQuery("FROM Excerpt", Excerpt.class);
        return query.getResultList();
    }

    @Override
    public Excerpt getExcerptByID(Long id) {
        return entityManager.find(Excerpt.class, id);
    }

    @Override
    @Transactional
    public Boolean deleteExcerptByID(Long id) {
        Excerpt excerpt = entityManager.find(Excerpt.class, id);
        entityManager.remove(excerpt);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Excerpt saveExcerpt(Excerpt excerpt) {
        entityManager.persist(excerpt);
        return excerpt;
    }

    @Override
    @Transactional
    public Excerpt updateExcerpt(Excerpt excerpt) {
        return entityManager.merge(excerpt);
    }
}
