package org.example.dao;

import org.example.data.DatabaseConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LedgerDAO {

    public ResultSet showAllLedgers(int userId) throws SQLException {
        String sql = "{CALL ShowAllLedgers(?)}";
        Connection conn = DatabaseConnection.getConnection();
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, userId);
        return stmt.executeQuery();
    }

    public ResultSet showAllDeposits(int userId) throws SQLException {
        String sql = "{CALL ShowAllDeposits(?)}";
        Connection conn = DatabaseConnection.getConnection();
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, userId);
        return stmt.executeQuery();
    }

    public ResultSet showAllPayments(int userId) throws SQLException {
        String sql = "{CALL ShowAllPayments(?)}";
        Connection conn = DatabaseConnection.getConnection();
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, userId);
        return stmt.executeQuery();
    }
}
