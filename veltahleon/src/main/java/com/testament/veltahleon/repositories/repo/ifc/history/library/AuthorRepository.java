package com.testament.veltahleon.repositories.repo.ifc.history.library;

import com.testament.veltahleon.model.entities.history.library.Author;

import java.util.Collection;

public interface AuthorRepository {

    Collection<Author> getAuthors();
    Author getAuthorByID(Long id);
    Boolean deleteAuthorByID(Long id);
    Author saveAuthor(Author author);
    Author updateAuthor(Author author);
}
