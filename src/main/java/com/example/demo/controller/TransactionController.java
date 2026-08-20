package com.example.demo.controller;

import com.example.demo.model.Transaction;
import com.example.demo.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
@RestController
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/transactions")
    public List<Transaction> getTransactionsByType(@RequestParam String type) throws SQLException {
        return transactionService.getTransactionsByType(type);
    }

    @GetMapping("/accounts/{accountId}/transactions")
    public List<Transaction> getTransactionsByAccountId(@PathVariable String accountId) throws SQLException {
        return transactionService.getTransactionsByAccountId(accountId);
    }

    @PostMapping("/transactions")
    public Transaction createTransaction(@RequestBody Transaction transaction) throws SQLException {
        return transactionService.createTransaction(transaction);
    }
}