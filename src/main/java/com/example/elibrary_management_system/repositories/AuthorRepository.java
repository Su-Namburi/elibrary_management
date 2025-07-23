package com.example.elibrary_management_system.repositories;

import com.example.elibrary_management_system.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
