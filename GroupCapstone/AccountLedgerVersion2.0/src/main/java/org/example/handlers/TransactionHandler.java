package org.example.handlers;

import org.example.dao.TransactionDAO;

import java.sql.SQLException;

public class TransactionHandler {

    private final TransactionDAO transactionDAO = new TransactionDAO();

    public void addDeposit(int userId, String description, String vendor, double amount) throws SQLException {
        transactionDAO.addDeposit(userId, description, vendor, amount);
    }

    public void makePayment(int userId, String description, String vendor, double amount) throws SQLException {
        transactionDAO.makePayment(userId, description, vendor, amount);
    }
}
