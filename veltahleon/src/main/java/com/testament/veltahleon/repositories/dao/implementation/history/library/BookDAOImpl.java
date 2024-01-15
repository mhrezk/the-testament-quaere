package com.testament.veltahleon.repositories.dao.implementation.history.library;

import com.testament.veltahleon.model.entities.history.library.Book;
import com.testament.veltahleon.repositories.dao.ifc.history.library.BookDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class BookDAOImpl implements BookDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Book> getBooks() {
        TypedQuery<Book> query = entityManager.createQuery("FROM Book", Book.class);
        return query.getResultList();
    }

    @Override
    public Book getBookByID(Long id) {
        return entityManager.find(Book.class, id);
    }

    @Override
    @Transactional
    public Boolean deleteBookByID(Long id) {
        Book book = entityManager.find(Book.class, id);
        entityManager.remove(book);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Book saveBook(Book book) {
        entityManager.persist(book);
        return book;
    }

    @Override
    @Transactional
    public Book updateBook(Book book) {
        return entityManager.merge(book);
    }
}
