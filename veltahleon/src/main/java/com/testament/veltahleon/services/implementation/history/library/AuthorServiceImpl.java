package com.testament.veltahleon.services.implementation.history.library;

import com.testament.veltahleon.model.entities.history.library.Author;
import com.testament.veltahleon.model.entities.history.library.Book;
import com.testament.veltahleon.repositories.history.library.AuthorRepository;
import com.testament.veltahleon.repositories.history.library.BookRepository;
import com.testament.veltahleon.services.ifc.history.library.AuthorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

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
    public Collection<Author> getAuthorByFirstName(String name) {
        return authorRepository.findByFirstName(name);
    }

    @Override
    public Collection<Author> getAuthorByLastName(String name) {
        return authorRepository.findByLastName(name);
    }

//    @Override
//    public Author getAuthorByUniqueID(String uniqueID) {
//        if(authorRepository.countByUniqueValue(uniqueID) <= 0) {
//            Author newAuthor = new Author();
//            newAuthor.setFirstName(firstName.toUpperCase());
//            newAuthor.setLastName(lastName.toUpperCase());
//            newAuthor.setImageURL(defaultImageURL("default.png"));
//            return authorRepository.save(newAuthor);
//        } else {
//            return authorRepository.findByUniqueValue(uniqueID);
//        }
//    }

    @Override
    public Author getAuthorByFirstNameAndLastName(String firstName, String lastName) {
        if(authorRepository.countByFirstNameAndLastName(firstName, lastName) <= 0) {
            Author newAuthor = new Author();
            newAuthor.setFirstName(firstName.toUpperCase());
            newAuthor.setLastName(lastName.toUpperCase());
            newAuthor.setImageURL(defaultImageURL("default.png"));
            return authorRepository.save(newAuthor);
        } else {
            return authorRepository.findByFirstNameAndLastName(firstName, lastName);
        }
    }

    @Override
    public Author getAuthorByFirstNameAndLastNameAndUniqueValue(String firstName, String lastName, String uniqueID) {
        if(authorRepository.countByUniqueValue(uniqueID) <= 0) {
            Author newAuthor = new Author();
            newAuthor.setFirstName(firstName.toUpperCase());
            newAuthor.setMiddleName("N/A");
            newAuthor.setPenName("N/A");
            newAuthor.setBirthYear(0);
            newAuthor.setBirthYearAbbreviation("N/A");
            newAuthor.setDeathYear(0);
            newAuthor.setImageURL(defaultImageURL("default.png"));
            newAuthor.setUniqueValue(generateUUID() + newAuthor.getFirstName().toLowerCase().charAt(0) + newAuthor.getLastName().toLowerCase().charAt(0) + authorRepository.count());
            return authorRepository.save(newAuthor);
        } else {
            return authorRepository.findByUniqueValue(uniqueID);
        }
    }

    @Override
    public Author getAuthorByIDAndFirstNameAndLastNameAndUniqueValue(Long id, String firstName, String lastName) {
        if(authorRepository.countByIdAndFirstNameAndLastName(id, firstName, lastName) <= 0) {
            Author newAuthor = new Author();
            newAuthor.setFirstName(firstName.toUpperCase());
            newAuthor.setMiddleName("N/A");
            newAuthor.setPenName("N/A");
            newAuthor.setBirthYear(0);
            newAuthor.setBirthYearAbbreviation("N/A");
            newAuthor.setDeathYear(0);
            newAuthor.setImageURL(defaultImageURL("default.png"));
            newAuthor.setUniqueValue(generateUUID() + newAuthor.getFirstName().toLowerCase().charAt(0) + newAuthor.getLastName().toLowerCase().charAt(0) + authorRepository.count());
            return authorRepository.save(newAuthor);
        } else {
            return authorRepository.findByIdAndFirstNameAndLastName(id, firstName, lastName);
        }
    }

//    @Override
//    public Author getAuthorByBookName(String name) {
//        return authorRepository.findByBooks_Name(name);
//    }

    @Override
    public Boolean deleteAuthorByID(Long id) {
        String firstName = authorRepository.findById(id).orElseThrow().getFirstName();
        String lastName = authorRepository.findById(id).orElseThrow().getLastName();
        List<Book> books = (List<Book>) bookRepository.findByAuthor_FirstNameAndAuthor_LastNameAndAuthor_Id(firstName, lastName, id);
        bookRepository.deleteAllById(books.stream().map(Book::getId).toList());
        authorRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Author saveAuthor(Author author) {
        author.setFirstName(author.getFirstName().toUpperCase());
        author.setLastName(author.getLastName().toUpperCase());
        if(author.getMiddleName() == null) {
            author.setMiddleName("N/A");
        } else {
            author.setMiddleName(author.getMiddleName().toUpperCase());
        }

        if(author.getPenName() == null) {
            author.setPenName("N/A");
        } else {
            author.setPenName(author.getPenName().toUpperCase());
        }

        author.setBirthYear(0);
        author.setBirthYearAbbreviation("N/A");
        author.setDeathYear(0);
        author.setImageURL(defaultImageURL("default.png"));
        author.setUniqueValue(generateUUID() + author.getGender().toString().charAt(0) + author.getFirstName().toLowerCase().charAt(0) + author.getLastName().toLowerCase().charAt(0) + (authorRepository.count() + 1));
        return authorRepository.save(author);
    }

    private String defaultImageURL(String imageName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/history/library/authors/images/" + imageName).toUriString();
    }

    @Override
    public Author updateAuthor(Long id, Author author) {
        Author newAuthor = authorRepository.findById(id).orElseThrow();

        if(author.getGender() != null && newAuthor.getGender() != author.getGender()) {
            newAuthor.setGender(author.getGender());
        }

        if(author.getFirstName() != null && newAuthor.getFirstName() != author.getFirstName()) {
            newAuthor.setFirstName(author.getFirstName().toUpperCase());
        }

        if(author.getMiddleName() != null && newAuthor.getMiddleName() != author.getMiddleName()) {
            newAuthor.setMiddleName(author.getMiddleName().toUpperCase());
        }

        if(author.getLastName() != null && newAuthor.getLastName() != author.getLastName()) {
            newAuthor.setLastName(author.getLastName().toUpperCase());
        }

        if(author.getPenName() != null && newAuthor.getPenName() != author.getPenName()) {
            newAuthor.setPenName(author.getPenName().toUpperCase());
        }

        if(author.getBirthYear() != null && newAuthor.getBirthYear() != author.getBirthYear()) {
            newAuthor.setBirthYear(author.getBirthYear());
        }

        if(author.getBirthYearAbbreviation() != null && newAuthor.getBirthYearAbbreviation() != author.getBirthYearAbbreviation()) {
            newAuthor.setBirthYearAbbreviation(author.getBirthYearAbbreviation());
        }

        if(author.getDeathYear() != null && newAuthor.getDeathYear() != author.getDeathYear()) {
            newAuthor.setDeathYear(author.getDeathYear());
        }

        if(author.getBiography() != null && newAuthor.getBiography() != author.getBiography()) {
            newAuthor.setBiography(author.getBiography());
        }

        if(author.getImageURL() != null && newAuthor.getImageURL() != author.getImageURL()) {
            newAuthor.setImageURL(author.getImageURL());
        }

//        if(author.getName() != null && newAuthor.getName() != author.getName()) {
//            newAuthor.setName(author.getName());
//        }

//        if(author.getGender() != null && newAuthor.getGender() != author.getGender()) {
//            newAuthor.setGender(author.getGender());
//        }
//
//        if(author.getRace() != null && newAuthor.getRace() != author.getRace()) {
//            newAuthor.setRace(author.getRace());
//        }
//
//        if(author.getBiography() != null && newAuthor.getBiography() != author.getBiography()) {
//            newAuthor.setBiography(author.getBiography());
//        }

//        if(author.getBooks() != null && newAuthor.getBooks() != author.getBooks()) {
//            newAuthor.setBooks(author.getBooks());
//        }

//        if(author.getBirthYear() != null && newAuthor.getBirthYear() != author.getBirthYear()) {
//            newAuthor.setBirthYear(author.getBirthYear());
//        }
//
//        if(author.getDeathYear() != null && newAuthor.getDeathYear() != author.getDeathYear()) {
//            newAuthor.setDeathYear(author.getDeathYear());
//        }

        return authorRepository.save(newAuthor);
    }

    @Override
    public Author modifyAuthor(Long id, Author author) {
        Author newAuthor = authorRepository.findById(id).orElseThrow();
        newAuthor.setFirstName(author.getFirstName().toUpperCase());
        newAuthor.setMiddleName(author.getMiddleName().toUpperCase());
        newAuthor.setPenName(author.getPenName().toUpperCase());
        newAuthor.setLastName(author.getLastName().toUpperCase());
        newAuthor.setBirthYear(author.getBirthYear());
        newAuthor.setDeathYear(author.getDeathYear());
        newAuthor.setBirthYearAbbreviation(author.getBirthYearAbbreviation());
        newAuthor.setImageURL(author.getImageURL());
        newAuthor.setBiography(author.getBiography());
        newAuthor.setGender(author.getGender());
        return authorRepository.save(newAuthor);
    }

    @Override
    public long countAuthors() {
        return authorRepository.count();
    }

    private String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
