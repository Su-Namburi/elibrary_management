package com.example.elibrary_management_system.repositories;

import com.example.elibrary_management_system.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    //this was causing the application to not start coz of a property name. I named status and transaction status. the name of the method did not have transaction status, it had status.
    // it was throwing an exception saying cannot find status coz it was looking for a getStatus when in reality the system has transactionStatus and not status
    Transaction findTopByBookAndStudentAndTransactionTypeAndTransactionStatusOrderByIdDesc(Book book, Student student, TransactionType transactionType, TransactionStatus transactionStatus);
}