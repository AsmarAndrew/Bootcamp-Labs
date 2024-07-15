package org.example.dao;

import org.example.data.DatabaseConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportDAO {

    public ResultSet showReportsByMonthToDate(int userId) throws SQLException {
        String sql = "{CALL ShowReportsByMonthToDate(?)}";
        Connection conn = DatabaseConnection.getConnection();
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, userId);
        return stmt.executeQuery();
    }

    public ResultSet showReportsByPreviousMonth(int userId) throws SQLException {
        String sql = "{CALL ShowReportsByPreviousMonth(?)}";
        Connection conn = DatabaseConnection.getConnection();
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, userId);
        return stmt.executeQuery();
    }

    public ResultSet showReportsByYearToDate(int userId) throws SQLException {
        String sql = "{CALL ShowReportsByYearToDate(?)}";
        Connection conn = DatabaseConnection.getConnection();
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, userId);
        return stmt.executeQuery();
    }

    public ResultSet showReportsByPreviousYear(int userId) throws SQLException {
        String sql = "{CALL ShowReportsByPreviousYear(?)}";
        Connection conn = DatabaseConnection.getConnection();
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, userId);
        return stmt.executeQuery();
    }

    public ResultSet searchByVendor(int userId, String vendor) throws SQLException {
        String sql = "{CALL SearchByVendor(?, ?)}";
        Connection conn = DatabaseConnection.getConnection();
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, userId);
        stmt.setString(2, vendor);
        return stmt.executeQuery();
    }

    public ResultSet customSearch(int userId, Date startDate, Date endDate, String vendor) throws SQLException {
        String sql = "{CALL CustomSearch(?, ?, ?, ?)}";
        Connection conn = DatabaseConnection.getConnection();
        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1, userId);
        stmt.setDate(2, startDate);
        stmt.setDate(3, endDate);
        stmt.setString(4, vendor.isEmpty() ? "%" : vendor);
        return stmt.executeQuery();
    }
}
