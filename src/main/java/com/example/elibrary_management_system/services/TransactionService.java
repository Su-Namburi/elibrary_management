package com.example.elibrary_management_system.services;

import com.example.elibrary_management_system.models.*;
import com.example.elibrary_management_system.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BookService  bookService;

    @Autowired
    private StudentService  studentService;

    @Value("${student.maxBooksAllowed}")
    private Integer maxBooksAllowed;

    @Value("${books.max.days.allowed}")
    private Integer maxDaysAllowed;

    @Value("${fine.per.day}")
    private Integer finePerDay;

    public String initiate(Long studentId, Long bookId, TransactionType transactionType) throws Exception {

        switch (transactionType) {
            case ISSUANCE:
                return initiateIssuance(studentId,bookId);
            case RETURN:
                return initiateReturn(studentId,bookId);
            default:
                throw new Exception("INVALID TRANSACTION TYPE");
        }
    }


    //    1.get student
//    2.get book
//    3.validations
//      1. student not present or book not present
//      2. if book is assigned to someone else
//      3. limit of student taking books reached
//    4. Create a transaction with status as pending
//    5. assign book to student
//    6. make status as success
//    7. if something went wrong make status as failed and handle accordingly
    private String initiateIssuance(Long studentId, Long bookId) throws Exception {
//        ************DATA RETRIEVAL ****************

        Book book = this.bookService.getBookById(bookId);
        Student student = this.studentService.getStudent(studentId).getStudent();


//        *******VALIDATIONS **************

        if(student == null){
            throw new Exception("student not present");
        }
        //book.getStudent() to check if book is assigned to a student
        if(book == null || book.getStudent() != null){
            throw new Exception("book is not available for issuance");
        }

        List<Book> issuedBooks = student.getBooks();
        if(issuedBooks != null && issuedBooks.size() > this.maxBooksAllowed){
            throw new Exception("maxBooksAllowed exceeded");
        }

//        ######## Issuance Logic ############

        Transaction transaction = Transaction
                .builder()
                .book(book)
                .student(student)
                .externalId(UUID.randomUUID().toString())
                .transactionType(TransactionType.ISSUANCE)
                .transactionStatus(TransactionStatus.PENDING)
                .build();

        this.transactionRepository.save(transaction);

        try {
            //assign book to student and save book
            book.setStudent(student);
            book = this.bookService.saveBook(book);
            //update transaction as success
            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
            this.transactionRepository.save(transaction);
        }catch (Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            this.transactionRepository.save(transaction);
            //unassign the book if something went wrong
            if(book.getStudent() != null){
                book.setStudent(null);
            }
            this.bookService.saveBook(book);
        }

        return transaction.getExternalId();
    }

    private String initiateReturn(Long studentId, Long bookId) throws Exception {
        //        ************DATA RETRIEVAL ****************

        Book book = this.bookService.getBookById(bookId);
        Student student = this.studentService.getStudent(studentId).getStudent();

        //        *******VALIDATIONS **************

        if(student == null){
            throw new Exception("student not present");
        }
        //check if book is assigned to the same student or not.
        if(book == null || book.getStudent() == null || book.getStudent().getId() != studentId){
            throw new Exception("book is not available for return");
        }

//        Return Logic
        Transaction transaction = Transaction
                .builder()
                .book(book)
                .student(student)
                .externalId(UUID.randomUUID().toString())
                .transactionType(TransactionType.RETURN)
                .transactionStatus(TransactionStatus.PENDING)
                .build();

        this.transactionRepository.save(transaction);

        try {
            int fine = getFine(book,student);
            //unassign book
            book.setStudent(null);
            //save book
            book = this.bookService.saveBook(book);

            transaction.setFine(fine);
            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
            this.transactionRepository.save(transaction);
        }catch (Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            this.transactionRepository.save(transaction);
            if(book.getStudent() == null){
                //undo the book assignment if something went wrong
                book.setStudent(student);
                book = this.bookService.saveBook(book);
            }
        }
        return transaction.getExternalId();
    }

    public int getFine(Book book, Student student) {
        //find the last transaction of the student and book and transaction type(issuance) and status(success)
        Transaction issuedTxn = this.transactionRepository.findTopByBookAndStudentAndTransactionTypeAndTransactionStatusOrderByIdDesc(
                book,student,TransactionType.ISSUANCE,TransactionStatus.SUCCESS);

        Long issuedTimeInMillis = issuedTxn.getUpdatedOn().getTime();
        Long timePassedInMillis = System.currentTimeMillis() - issuedTimeInMillis;

        Long daysPassed =  TimeUnit.DAYS.convert(timePassedInMillis, TimeUnit.MILLISECONDS);

        if(daysPassed > maxDaysAllowed){
            return (daysPassed.intValue() - this.maxDaysAllowed)*this.finePerDay;
        }
        return 0;
    }
    public int sumWithIncrement(int a, int b) {
        return a + b + 1;
    }
}