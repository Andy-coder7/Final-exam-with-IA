package com.example.demo.controller;

import com.example.demo.model.Account;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@AllArgsConstructor
@RestController
public class AccountController {
    private final AccountService accountService;
    @GetMapping("/account/{id}")
    public Account getAccountById(@PathVariable String id) {
       return accountService.getAccountById(id);
    }
    @GetMapping("/account/{id}/balance")
    public BigDecimal getBalance(@PathVariable String id) {
        return accountService.getBalance(id);
    }
}
