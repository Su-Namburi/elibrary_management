package com.example.elibrary_management_system.services;

import com.example.elibrary_management_system.models.Author;
import com.example.elibrary_management_system.models.Book;
import com.example.elibrary_management_system.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;

    public Long createBook(Book book) {

        Author author = book.getAuthor();
        this.authorService.createAuthor(author);
        return bookRepository.save(book).getId();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book saveBook(Book book) {
        return this.bookRepository.save(book);
    }
}
