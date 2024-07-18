package org.example.dao;

import org.example.data.DatabaseConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionDAO {

    public void addDeposit(int userId, String description, String vendor, double amount) throws SQLException {
        String sql = "{CALL AddDeposit(?, ?, ?, ?)}";

        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, userId);
            stmt.setString(2, description);
            stmt.setString(3, vendor);
            stmt.setDouble(4, amount);
            stmt.executeUpdate();
        }
    }

    public void makePayment(int userId, String description, String vendor, double amount) throws SQLException {
        String sql = "{CALL MakePayment(?, ?, ?, ?)}";

        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, userId);
            stmt.setString(2, description);
            stmt.setString(3, vendor);
            stmt.setDouble(4, amount);
            stmt.executeUpdate();
        }
    }
}
