package com.example.elibrary_management_system.controllers;

import com.example.elibrary_management_system.models.TransactionType;
import com.example.elibrary_management_system.models.User;
import com.example.elibrary_management_system.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/initiate")
    public String initiateTransaction(@RequestParam("bookId") Long bookId,
                                      @RequestParam("transactionType") TransactionType transactionType) throws Exception {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        User user = (User) authentication.getPrincipal();
        Long studentId = user.getStudent().getId();

        return this.transactionService.initiate(studentId,bookId,transactionType);
    }
}
