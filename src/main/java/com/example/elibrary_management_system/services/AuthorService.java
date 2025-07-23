package com.example.elibrary_management_system.services;

import com.example.elibrary_management_system.models.Author;
import com.example.elibrary_management_system.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

}
