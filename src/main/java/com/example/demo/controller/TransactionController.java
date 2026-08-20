package com.example.demo.controller;

import com.example.demo.model.Transaction;
import com.example.demo.model.TransactionType;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class TransactionController {
    private TransactionService transactionService;
    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions(@RequestParam(required = false) TransactionType type) {
        return transactionService.getAllTransactions();
    }
    @GetMapping("/accounts/{accountId}/transaction")
    public List<Transaction> getTransactions(@PathVariable String accountId) {
        return transactionService.getTransactionByAccount(accountId);
    }
    @PostMapping("/transaction")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.createTransaction();
    }
}
