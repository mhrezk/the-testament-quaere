package com.testament.veltahleon.services.implementation.history.library;

import com.testament.veltahleon.model.entities.history.library.Author;
import com.testament.veltahleon.repositories.history.library.AuthorRepository;
import com.testament.veltahleon.services.ifc.history.library.AuthorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Collection<Author> getAuthorsWithPagination(int pageNumber, int numberOfRecords) {
        return authorRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorByID(Long id) {
        return authorRepository.findById(id).orElseThrow();
    }

    @Override
    public Author getAuthorByName(String name) {
        return authorRepository.findByName(name);
    }

    @Override
    public Author getAuthorByBookName(String name) {
        return authorRepository.findByBooks_Name(name);
    }

    @Override
    public Boolean deleteAuthorByID(Long id) {
        authorRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Long id, Author author) {
        Author newAuthor = authorRepository.findById(id).orElseThrow();

        if(author.getName() != null && newAuthor.getName() != author.getName()) {
            newAuthor.setName(author.getName());
        }

        if(author.getGender() != null && newAuthor.getGender() != author.getGender()) {
            newAuthor.setGender(author.getGender());
        }

        if(author.getRace() != null && newAuthor.getRace() != author.getRace()) {
            newAuthor.setRace(author.getRace());
        }

        if(author.getBiography() != null && newAuthor.getBiography() != author.getBiography()) {
            newAuthor.setBiography(author.getBiography());
        }

        if(author.getBooks() != null && newAuthor.getBooks() != author.getBooks()) {
            newAuthor.setBooks(author.getBooks());
        }

        if(author.getBirthYear() != null && newAuthor.getBirthYear() != author.getBirthYear()) {
            newAuthor.setBirthYear(author.getBirthYear());
        }

        if(author.getDeathYear() != null && newAuthor.getDeathYear() != author.getDeathYear()) {
            newAuthor.setDeathYear(author.getDeathYear());
        }

        return authorRepository.save(newAuthor);
    }
}
