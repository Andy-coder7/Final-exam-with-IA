package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@AllArgsConstructor
@Builder
@Data
public class Transaction {
    private final String id;
    private final Instant createdAt;
    private final TransactionType transactionType;
    private final BigDecimal amount;
    private final String reason;
}
