package com.testament.veltahleon.services.dao.implementation.history.library;

import com.testament.veltahleon.model.entities.history.library.Book;
import com.testament.veltahleon.repositories.dao.ifc.history.library.BookDAO;
import com.testament.veltahleon.services.dao.ifc.history.library.BookService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDAO bookDAO;

    @Override
    public Collection<Book> getBooks() {
        return bookDAO.getBooks();
    }

    @Override
    public Book getBookByID(Long id) {
        return bookDAO.getBookByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteBookByID(Long id) {
        return bookDAO.deleteBookByID(id);
    }

    @Override
    @Transactional
    public Book saveBook(Book book) {
        return bookDAO.saveBook(book);
    }

    @Override
    @Transactional
    public Book updateBook(Book book) {
        return bookDAO.updateBook(book);
    }
}
