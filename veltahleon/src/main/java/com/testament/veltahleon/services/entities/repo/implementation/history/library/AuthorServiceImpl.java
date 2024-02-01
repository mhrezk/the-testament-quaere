package com.testament.veltahleon.services.entities.repo.implementation.history.library;

import com.testament.veltahleon.model.entities.history.library.Author;
import com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.history.library.AuthorRepository;
import com.testament.veltahleon.services.entities.repo.ifc.history.library.AuthorService;
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
        return authorRepository.findByBook_Name(name);
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
        compareOldAuthorWithNewAuthor(author, newAuthor);
        return authorRepository.save(newAuthor);
    }

    //Helper Methods
    private void compareOldAuthorWithNewAuthor(Author oldAuthor, Author newAuthor) {
        if(oldAuthor.getName() != null && newAuthor.getName() != oldAuthor.getName()) {
            newAuthor.setName(oldAuthor.getName());
        }

        if(oldAuthor.getGender() != null && newAuthor.getGender() != oldAuthor.getGender()) {
            newAuthor.setGender(oldAuthor.getGender());
        }

        if(oldAuthor.getRace() != null && newAuthor.getRace() != oldAuthor.getRace()) {
            newAuthor.setRace(oldAuthor.getRace());
        }

        if(oldAuthor.getBiography() != null && newAuthor.getBiography() != oldAuthor.getBiography()) {
            newAuthor.setBiography(oldAuthor.getBiography());
        }

        if(oldAuthor.getBooks() != null && newAuthor.getBooks() != oldAuthor.getBooks()) {
            newAuthor.setBooks(oldAuthor.getBooks());
        }

        if(oldAuthor.getYearBirthAndDeath() != null && newAuthor.getYearBirthAndDeath() != oldAuthor.getYearBirthAndDeath()) {
            newAuthor.setYearBirthAndDeath(oldAuthor.getYearBirthAndDeath());
        }
    }
}
