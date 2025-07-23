package com.example.elibrary_management_system.controllers;

import com.example.elibrary_management_system.dtos.CreateBookRequest;
import com.example.elibrary_management_system.models.Book;
import com.example.elibrary_management_system.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/create")
    public Long createBook(@Valid @RequestBody CreateBookRequest createBookRequest) {
        return this.bookService.createBook(createBookRequest.toBook());
    }

    @GetMapping("/get/{id}")
    public Book getBook(@PathVariable Long id) {
        return this.bookService.getBookById(id);
    }

}
