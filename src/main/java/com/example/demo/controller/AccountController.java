package com.example.demo.controller;

import com.example.demo.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.SQLException;

@AllArgsConstructor
@RestController
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/accounts/{id}/balance")
    public BigDecimal getBalance(@PathVariable String id) throws SQLException {
        return accountService.getAccountBalance(id);
    }
}