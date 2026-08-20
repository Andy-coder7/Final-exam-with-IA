package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
public class Account {
    private final String id;
    private final AccountType accountType;
    private final List<Transaction> transactions;
}
