package com.example.demo.repository;

import com.example.demo.model.Account;
import com.example.demo.model.Transaction;
import com.example.demo.model.TransactionType;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {

    private final Connection connection;

    public TransactionRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Transaction> findByType(TransactionType type) throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT t.id, t.created_at, t.transaction_type, t.amount, t.reason, t.account_id " +
                "FROM transaction t WHERE t.transaction_type = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, type.name());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    transactions.add(mapResultSetToTransaction(resultSet));
                }
            }
        }
        return transactions;
    }

    public List<Transaction> findByAccountId(String accountId) throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT t.id, t.created_at, t.transaction_type, t.amount, t.reason, t.account_id " +
                "FROM transaction t WHERE t.account_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, accountId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    transactions.add(mapResultSetToTransaction(resultSet));
                }
            }
        }
        return transactions;
    }

    public Transaction save(Transaction transaction) throws SQLException {
        String sql = "INSERT INTO transaction (id, created_at, transaction_type, amount, reason, account_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, transaction.getId());
            statement.setTimestamp(2, Timestamp.from(transaction.getCreatedAt()));
            statement.setString(3, transaction.getTransactionType().name());
            statement.setBigDecimal(4, transaction.getAmount());
            statement.setString(5, transaction.getReason());
            statement.setString(6, transaction.getAccount() != null ? transaction.getAccount().getId() : null);

            statement.executeUpdate();
        }
        return transaction;
    }

    private Transaction mapResultSetToTransaction(ResultSet rs) throws SQLException {
        Timestamp timestamp = rs.getTimestamp("created_at");
        String accountId = rs.getString("account_id");

        Account account = null;
        if (accountId != null) {
            account = Account.builder()
                    .id(accountId)
                    .build();
        }

        return Transaction.builder()
                .id(rs.getString("id"))
                .createdAt(timestamp != null ? timestamp.toInstant() : null)
                .transactionType(TransactionType.valueOf(rs.getString("transaction_type")))
                .amount(rs.getBigDecimal("amount"))
                .reason(rs.getString("reason"))
                .account(account)
                .build();
    }
}