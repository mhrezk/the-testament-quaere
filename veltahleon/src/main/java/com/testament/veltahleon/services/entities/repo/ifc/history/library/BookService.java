package com.testament.veltahleon.services.entities.repo.ifc.history.library;

import com.testament.veltahleon.model.entities.history.library.Book;

import java.util.Collection;

public interface BookService {

    Collection<Book> getBooks();
    Book getBookByID(Long id);
    Boolean deleteBookByID(Long id);
    Book saveBook(Book book);
    Book updateBook(Book book);
}
