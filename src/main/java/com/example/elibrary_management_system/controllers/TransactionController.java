package com.example.elibrary_management_system.controllers;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/initiate")
    public String initiateTransaction(@RequestParam("studentId") Long studentId,
                                      @RequestParam("bookId") Long bookId,
                                      @RequestParam("transactionType")TransactionType transactionType) throws Exception {

        return this.transactionService.initiate(studentId,bookId,transactionType);
    }
}
