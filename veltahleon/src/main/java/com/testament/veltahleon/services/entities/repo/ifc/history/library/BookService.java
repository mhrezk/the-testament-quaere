package com.testament.veltahleon.services.entities.repo.ifc.history.library;

import com.testament.veltahleon.model.entities.history.library.Book;

import java.util.Collection;

public interface BookService {

    Collection<Book> getBooksWithPagination(int pageNumber, int numberOfRecords);
    Collection<Book> getBooks();
    Book getBookByID(Long id);
    Book getBookByBookName(String name);
    Book getBookByAuthorName(String name);
    Boolean deleteBookByID(Long id);
    Book saveBook(Book book);
    Book updateBook(Long id, Book book);
}
