package com.example.elibrary_management_system.repositories;

import com.example.elibrary_management_system.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findTopByBookAndStudentAndTransactionTypeAndStatusOrderByIdDesc(Book book, Student student, TransactionType transactionType, TransactionStatus transactionStatus);
}