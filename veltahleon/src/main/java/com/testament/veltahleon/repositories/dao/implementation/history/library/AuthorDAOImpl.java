package com.testament.veltahleon.repositories.dao.implementation.history.library;

import com.testament.veltahleon.model.entities.history.library.Author;
import com.testament.veltahleon.repositories.dao.ifc.history.library.AuthorDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class AuthorDAOImpl implements AuthorDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Author> getAuthors() {
        TypedQuery<Author> query = entityManager.createQuery("FROM Author", Author.class);
        return query.getResultList();
    }

    @Override
    public Author getAuthorByID(Long id) {
        return entityManager.find(Author.class, id);
    }

    @Override
    @Transactional
    public Boolean deleteAuthorByID(Long id) {
        Author author = entityManager.find(Author.class, id);
        entityManager.remove(author);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Author saveAuthor(Author author) {
        entityManager.persist(author);
        return author;
    }

    @Override
    @Transactional
    public Author updateAuthor(Author author) {
        return entityManager.merge(author);
    }
}
