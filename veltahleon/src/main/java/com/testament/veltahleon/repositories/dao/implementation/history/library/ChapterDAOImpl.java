package com.testament.veltahleon.repositories.dao.implementation.history.library;

import com.testament.veltahleon.model.entities.history.library.Chapter;
import com.testament.veltahleon.repositories.dao.ifc.history.library.ChapterDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class ChapterDAOImpl implements ChapterDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Chapter> getChapters() {
        TypedQuery<Chapter> query = entityManager.createQuery("FROM Chapter", Chapter.class);
        return query.getResultList();
    }

    @Override
    public Chapter getChapterByID(Long id) {
        return entityManager.find(Chapter.class, id);
    }

    @Override
    @Transactional
    public Boolean deleteChapterByID(Long id) {
        Chapter chapter = entityManager.find(Chapter.class, id);
        entityManager.remove(chapter);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Chapter saveChapter(Chapter chapter) {
        entityManager.persist(chapter);
        return chapter;
    }

    @Override
    @Transactional
    public Chapter updateChapter(Chapter chapter) {
        return entityManager.merge(chapter);
    }
}
