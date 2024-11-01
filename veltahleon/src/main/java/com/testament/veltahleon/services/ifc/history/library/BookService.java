package com.testament.veltahleon.services.ifc.history.library;

import com.testament.veltahleon.model.entities.history.library.Book;

import java.util.Collection;

public interface BookService {

    Collection<Book> getBooksWithPagination(int pageNumber, int numberOfRecords);
    Collection<Book> getBooksWithPaginationByAuthorName(Long id, String firstName, String lastName, int pageNumber, int numberOfRecords);
    Collection<Book> getBooks();
    Collection<Book> getBooksByAuthorFirstName(String name);
    Collection<Book> getBooksByAuthorFirstNameAndAuthorLastNameAndAuthorID(String firstName, String lastName, Long id);
    Book getBookByID(Long id);
    Book getBookByBookName(String name);
    Book saveBook(Book book, String authorFirstName, String authorLastName, Long authorID);
    Book updateBook(Long id, Book book);
    Book modifyBook(Long id, Book book);
    Boolean deleteBookByID(Long id);
    long countBooks();
    long countBooksByAuthorNameAndAuthorID(Long id, String firstName, String lastName);
}
