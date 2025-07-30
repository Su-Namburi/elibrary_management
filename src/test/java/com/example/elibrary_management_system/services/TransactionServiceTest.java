package com.example.elibrary_management_system.services;

import com.example.elibrary_management_system.models.*;
import com.example.elibrary_management_system.repositories.TransactionRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.ReflectionUtils;


import java.util.Date;

//@RunWith(MockitoJUnitRunner.class)

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Test
    public void testGetFine() {

        Book book = Book.builder().id(1L).title("test 101").build();
        Student student = Student.builder().id(1L).studentName("test student").build();

        Transaction transaction = Transaction.builder()
                .book(book)
                .student(student)
                .transactionType(TransactionType.ISSUANCE)
                .transactionStatus(TransactionStatus.SUCCESS).updatedOn(new Date(1751211443000L)).build();


        Mockito.when(transactionRepository.findTopByBookAndStudentAndTransactionTypeAndTransactionStatusOrderByIdDesc(
                Mockito.eq(book),Mockito.eq(student),Mockito.eq(TransactionType.ISSUANCE),Mockito.eq(TransactionStatus.SUCCESS)
        )).thenReturn(transaction);

        ReflectionTestUtils.setField(transactionService, "maxDaysAllowed", 15);
        ReflectionTestUtils.setField(transactionService, "finePerDay", 1);

        int result = transactionService.getFine(book, student);
        Assert.assertEquals(150,result);

    }
}

//    @Test
//    public void sumWithIncrementTest() {
//        TransactionService transactionService = new TransactionService();
//        int result = transactionService.sumWithIncrement(1, 2);
//        Assert.assertEquals(4, result);
//    }
