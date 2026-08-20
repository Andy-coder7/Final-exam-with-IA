package com.example.demo.service;

import com.example.demo.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public BigDecimal getAccountBalance(String accountId) throws SQLException {
        if (accountId == null || accountId.isBlank()) {
            throw new IllegalArgumentException("L'identifiant du compte est requis.");
        }
        return accountRepository.getBalanceByAccountId(accountId);
    }
}