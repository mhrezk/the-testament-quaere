package com.testament.veltahleon.services.ifc.history.library;

import com.testament.veltahleon.model.entities.history.library.Author;

import java.util.Collection;

public interface AuthorService {

    Collection<Author> getAuthorsWithPagination(int pageNumber, int numberOfRecords);
    Collection<Author> getAuthors();
    Author getAuthorByID(Long id);
    Author getAuthorByName(String name);
    Author getAuthorByBookName(String name);
    Boolean deleteAuthorByID(Long id);
    Author saveAuthor(Author author);
    Author updateAuthor(Long id, Author author);
}
