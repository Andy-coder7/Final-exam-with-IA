package com.example.demo.repository;

import com.example.demo.model.Account;
import com.example.demo.model.AccountType;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;

@Repository
public class AccountRepository {

    private final Connection connection;

    public AccountRepository(Connection connection) {
        this.connection = connection;
    }

    public BigDecimal getBalanceByAccountId(String accountId) throws SQLException {
        String sql = "SELECT COALESCE(SUM(CASE WHEN transaction_type = 'IN' THEN amount ELSE -amount END), 0) AS balance " +
                "FROM transaction WHERE account_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, accountId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBigDecimal("balance");
                }
            }
        }
        return BigDecimal.ZERO;
    }
}