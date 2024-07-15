package org.example.handlers;

import org.example.dao.LedgerDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LedgerHandler {

    private final LedgerDAO ledgerDAO = new LedgerDAO();

    public ResultSet showAllLedgers(int userId) throws SQLException {
        return ledgerDAO.showAllLedgers(userId);
    }

    public ResultSet showAllDeposits(int userId) throws SQLException {
        return ledgerDAO.showAllDeposits(userId);
    }

    public ResultSet showAllPayments(int userId) throws SQLException {
        return ledgerDAO.showAllPayments(userId);
    }
}
