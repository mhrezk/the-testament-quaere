package com.testament.veltahleon.repositories.dao.implementation.history;

import com.testament.veltahleon.model.entities.history.Language;
import com.testament.veltahleon.repositories.dao.ifc.history.LanguageDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class LanguageDAOImpl implements LanguageDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Language> getLanguages() {
        TypedQuery<Language> query = entityManager.createQuery("FROM Language", Language.class);
        return query.getResultList();
    }

    @Override
    public Language getLanguageByID(Long id) {
        return entityManager.find(Language.class, id);
    }

    @Override
    public Boolean deleteLanguageByID(Long id) {
        Language language = entityManager.find(Language.class, id);
        entityManager.remove(language);
        return Boolean.TRUE;
    }

    @Override
    public Language saveLanguage(Language language) {
        entityManager.persist(language);
        return language;
    }

    @Override
    public Language updateLanguage(Language language) {
        return entityManager.merge(language);
    }
}
