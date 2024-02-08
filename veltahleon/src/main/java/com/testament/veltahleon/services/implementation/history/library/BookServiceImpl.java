package com.testament.veltahleon.services.implementation.history.library;

import com.testament.veltahleon.model.entities.history.library.Book;
import com.testament.veltahleon.repositories.history.library.BookRepository;
import com.testament.veltahleon.services.ifc.history.library.BookService;
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
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Collection<Book> getBooksWithPagination(int pageNumber, int numberOfRecords) {
        return bookRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
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

    @Override
    public Book getBookByAuthorName(String name) {
        return bookRepository.findByAuthor_Name(name);
    }

    @Override
    public Boolean deleteBookByID(Long id) {
        bookRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book book) {
        Book newBook = bookRepository.findById(id).orElseThrow();

        if(book.getName() != null && newBook.getName() != book.getName()) {
            newBook.setName(book.getName());
        }

        if(book.getAuthor() != null && newBook.getAuthor() != book.getAuthor()) {
            newBook.setAuthor(book.getAuthor());
        }

        if(book.getChapters() != null && newBook.getChapters() != book.getChapters()) {
            newBook.setChapters(book.getChapters());
        }

        return bookRepository.save(newBook);
    }
}
