package com.example.elibrary_management_system.repositories;

import com.example.elibrary_management_system.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
