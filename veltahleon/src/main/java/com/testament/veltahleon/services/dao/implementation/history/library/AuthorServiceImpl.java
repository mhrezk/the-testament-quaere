package com.testament.veltahleon.services.dao.implementation.history.library;

import com.testament.veltahleon.model.entities.history.library.Author;
import com.testament.veltahleon.repositories.dao.ifc.history.library.AuthorDAO;
import com.testament.veltahleon.services.dao.ifc.history.library.AuthorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorDAO authorDAO;

    @Override
    public Collection<Author> getAuthors() {
        return authorDAO.getAuthors();
    }

    @Override
    public Author getAuthorByID(Long id) {
        return authorDAO.getAuthorByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteAuthorByID(Long id) {
        return authorDAO.deleteAuthorByID(id);
    }

    @Override
    @Transactional
    public Author saveAuthor(Author author) {
        return authorDAO.saveAuthor(author);
    }

    @Override
    @Transactional
    public Author updateAuthor(Author author) {
        return authorDAO.updateAuthor(author);
    }
}
