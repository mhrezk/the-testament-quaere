package com.testament.veltahleon.repositories.dao.implementation.history;

import com.testament.veltahleon.model.entities.history.Letter;
import com.testament.veltahleon.repositories.dao.ifc.history.LetterDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class LetterDAOImpl implements LetterDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Letter> getLetters() {
        TypedQuery<Letter> query = entityManager.createQuery("FROM Letter", Letter.class);
        return query.getResultList();
    }

    @Override
    public Letter getLetterByID(Long id) {
        return entityManager.find(Letter.class, id);
    }

    @Override
    public Boolean deleteLetterByID(Long id) {
        Letter letter = entityManager.find(Letter.class, id);
        entityManager.remove(letter);
        return Boolean.TRUE;
    }

    @Override
   public Letter saveLetter(Letter letter) {
        entityManager.persist(letter);
        return letter;
    }

    @Override
    public Letter updateLetter(Letter letter) {
        return entityManager.merge(letter);
    }
}
