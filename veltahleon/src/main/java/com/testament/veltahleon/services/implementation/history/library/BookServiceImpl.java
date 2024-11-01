package com.testament.veltahleon.services.implementation.history.library;

import com.testament.veltahleon.model.entities.history.library.Author;
import com.testament.veltahleon.model.entities.history.library.Book;
import com.testament.veltahleon.repositories.history.library.AuthorRepository;
import com.testament.veltahleon.repositories.history.library.BookRepository;
import com.testament.veltahleon.services.ifc.history.library.BookService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Collection<Book> getBooksWithPagination(int pageNumber, int numberOfRecords) {
        return bookRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Book> getBooksWithPaginationByAuthorName(Long id, String firstName, String lastName, int pageNumber, int numberOfRecords) {
        return bookRepository.findByAuthor_IdAndAuthor_FirstNameAndAuthor_LastName(id, firstName, lastName, PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookByID(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    @Override
    public Book getBookByBookName(String name) {
        return bookRepository.findByName(name);
    }

//    @Override
//    public Book getBookByAuthorFirstNameAndAuthorLastName(String firstName, String lastName) {
//        return bookRepository.findByAuthor_FirstNameAndAuthor_LastName(firstName, lastName);
//    }

    @Override
    public Book saveBook(Book book, String authorFirstName, String authorLastName, Long id) {
        book.setName(book.getName().toUpperCase());
        Author author = authorRepository.findByIdAndFirstNameAndLastName(id, authorFirstName, authorLastName);
        book.setCoverURL(defaultImageURL("quran.png"));
        book.setReleaseDate(0);
        book.setReleaseYearAbbreviation("N/A");
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    private String defaultImageURL(String imageName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/history/library/books/images/" + imageName).toUriString();
    }

    @Override
    public Collection<Book> getBooksByAuthorFirstName(String name) {
        return bookRepository.findByAuthor_FirstName(name);
    }

    @Override
    public Collection<Book> getBooksByAuthorFirstNameAndAuthorLastNameAndAuthorID(String firstName, String lastName, Long id) {
        return bookRepository.findByAuthor_FirstNameAndAuthor_LastNameAndAuthor_Id(firstName, lastName, id);
    }

    @Override
    public Boolean deleteBookByID(Long id) {
        bookRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public long countBooks() {
        return bookRepository.count();
    }

    @Override
    public long countBooksByAuthorNameAndAuthorID(Long id, String firstName, String lastName) {
        return bookRepository.countByAuthor_IdAndAuthor_FirstNameAndAuthor_LastName(id, firstName, lastName);
    }

    @Override
    public Book updateBook(Long id, Book book) {
        Book newBook = bookRepository.findById(id).orElseThrow();

        if(book.getName() != null && newBook.getName() != book.getName()) {
            newBook.setName(book.getName().toUpperCase());
        }

        if(book.getDescription() != null && newBook.getDescription() != book.getDescription()) {
            newBook.setDescription(book.getDescription());
        }

        if(book.getCoverURL() != null && newBook.getCoverURL() != book.getCoverURL()) {
            newBook.setCoverURL(book.getCoverURL());
        }

//        if(book.getAuthor() != null && newBook.getAuthor() != book.getAuthor()) {
//            newBook.setAuthor(book.getAuthor());
//        }

//        if(book.getChapters() != null && newBook.getChapters() != book.getChapters()) {
//            newBook.setChapters(book.getChapters());
//        }

        return bookRepository.save(newBook);
    }

    @Override
    public Book modifyBook(Long id, Book book) {
        Book newBook = bookRepository.findById(id).orElseThrow();
        newBook.setName(book.getName().toUpperCase());
        newBook.setDescription(book.getDescription());
        newBook.setCoverURL(book.getCoverURL());
        //newBook.setAuthor(book.getAuthor());
        return bookRepository.save(newBook);
    }
}
