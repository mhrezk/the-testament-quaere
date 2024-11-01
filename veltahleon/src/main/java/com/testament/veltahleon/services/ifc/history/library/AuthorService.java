package com.testament.veltahleon.services.ifc.history.library;

import com.testament.veltahleon.model.entities.history.library.Author;

import java.util.Collection;

public interface AuthorService {

    Collection<Author> getAuthorsWithPagination(int pageNumber, int numberOfRecords);
    Collection<Author> getAuthors();
    Collection<Author> getAuthorByFirstName(String name);
    Collection<Author> getAuthorByLastName(String name);
    //Author getAuthorByUniqueID(String uniqueID);
    Author getAuthorByFirstNameAndLastName(String firstName, String lastName);
    Author getAuthorByFirstNameAndLastNameAndUniqueValue(String firstName, String lastName, String uniqueID);
    Author getAuthorByIDAndFirstNameAndLastNameAndUniqueValue(Long id, String firstName, String lastName);
    Author getAuthorByID(Long id);
    //Author getAuthorByBookName(String name);
    Boolean deleteAuthorByID(Long id);
    Author saveAuthor(Author author);
    Author updateAuthor(Long id, Author author);
    Author modifyAuthor(Long id, Author author);
    long countAuthors();
}
