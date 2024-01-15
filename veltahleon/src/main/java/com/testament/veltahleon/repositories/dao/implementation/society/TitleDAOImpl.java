package com.testament.veltahleon.repositories.dao.implementation.society;

import com.testament.veltahleon.model.entities.society.Title;
import com.testament.veltahleon.repositories.dao.ifc.society.TitleDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class TitleDAOImpl implements TitleDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Title> getTitles() {
        TypedQuery<Title> query = entityManager.createQuery("FROM Title", Title.class);
        return query.getResultList();
    }

    @Override
    public Title getTitleByID(Long id) {
        return entityManager.find(Title.class, id);
    }

    @Override
    public Boolean deleteTitleByID(Long id) {
        Title title = entityManager.find(Title.class, id);
        entityManager.remove(title);
        return Boolean.TRUE;
    }

    @Override
    public Title saveTitle(Title title) {
        entityManager.persist(title);
        return title;
    }

    @Override
    public Title updateTitle(Title title) {
        return entityManager.merge(title);
    }
}
