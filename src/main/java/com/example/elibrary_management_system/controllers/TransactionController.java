package com.example.elibrary_management_system.controllers;

import com.example.elibrary_management_system.models.TransactionType;
import com.example.elibrary_management_system.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/initiate")
    public String initiateTransaction(@RequestParam("studentId") Long studentId,
                                      @RequestParam("bookId") Long bookId,
                                      @RequestParam("transactionType") TransactionType transactionType) throws Exception {

        return this.transactionService.initiate(studentId,bookId,transactionType);
    }
}
