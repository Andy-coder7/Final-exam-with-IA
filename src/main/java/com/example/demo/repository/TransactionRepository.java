package com.example.demo.repository;

import com.example.demo.model.Transaction;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {
    private final Connection connection;

    public TransactionRepository(Connection connection) {
        this.connection = connection;
    }



    public List<Transaction> findByAccountId(Long accountId) throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transaction WHERE account_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, accountId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                }
            }
        }
        return transactions;
    }

    public Transaction save(Transaction transaction) throws SQLException {
        String sql = "INSERT INTO transaction (account_id, type, amount, date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, transaction.getId());
            statement.setString(2, transaction.getTransactionType().toString());
            statement.setBigDecimal(3, transaction.getAmount());
            statement.setTimestamp(4, Timestamp.from(transaction.getCreatedAt()));
            statement.executeUpdate();
        }
        return transaction;
    }
}