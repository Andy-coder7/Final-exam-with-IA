package com.example.demo.service;

import com.example.demo.model.Transaction;
import com.example.demo.model.TransactionType;
import com.example.demo.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getTransactionsByType(String typeStr) throws SQLException {
        if (typeStr == null || typeStr.isBlank()) {
            throw new IllegalArgumentException("Le type de transaction est obligatoire.");
        }


        TransactionType type;
        try {
            type = TransactionType.valueOf(typeStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Type de transaction invalide. Valeurs attendues : IN ou OUT.");
        }

        return transactionRepository.findByType(type);
    }


    public List<Transaction> getTransactionsByAccountId(String accountId) throws SQLException {
        if (accountId == null || accountId.isBlank()) {
            throw new IllegalArgumentException("L'identifiant du compte ne peut pas être vide.");
        }
        return transactionRepository.findByAccountId(accountId);
    }


    public Transaction createTransaction(Transaction transaction) throws SQLException {
        if (transaction == null) {
            throw new IllegalArgumentException("La transaction ne peut pas être nulle.");
        }
        if (transaction.getAccount() == null || transaction.getAccount().getId() == null) {
            throw new IllegalArgumentException("La transaction doit être associée à un compte valide.");
        }
        if (transaction.getAmount() == null || transaction.getAmount().doubleValue() <= 0) {
            throw new IllegalArgumentException("Le montant doit être supérieur à zéro.");
        }
        if (transaction.getTransactionType() == null) {
            throw new IllegalArgumentException("Le type de transaction est requis.");
        }

        Transaction transactionToSave = Transaction.builder()
                .id(transaction.getId() != null ? transaction.getId() : UUID.randomUUID().toString())
                .createdAt(transaction.getCreatedAt() != null ? transaction.getCreatedAt() : Instant.now())
                .transactionType(transaction.getTransactionType())
                .amount(transaction.getAmount())
                .reason(transaction.getReason())
                .account(transaction.getAccount())
                .build();

        return transactionRepository.save(transactionToSave);
    }
}