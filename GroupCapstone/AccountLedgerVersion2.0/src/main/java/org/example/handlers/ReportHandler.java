package org.example.handlers;

import org.example.dao.ReportDAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportHandler {

    private final ReportDAO reportDAO = new ReportDAO();

    public ResultSet showReportsByMonthToDate(int userId) throws SQLException {
        return reportDAO.showReportsByMonthToDate(userId);
    }

    public ResultSet showReportsByPreviousMonth(int userId) throws SQLException {
        return reportDAO.showReportsByPreviousMonth(userId);
    }

    public ResultSet showReportsByYearToDate(int userId) throws SQLException {
        return reportDAO.showReportsByYearToDate(userId);
    }

    public ResultSet showReportsByPreviousYear(int userId) throws SQLException {
        return reportDAO.showReportsByPreviousYear(userId);
    }

    public ResultSet searchByVendor(int userId, String vendor) throws SQLException {
        return reportDAO.searchByVendor(userId, vendor);
    }

    public ResultSet customSearch(int userId, Date startDate, Date endDate, String vendor) throws SQLException {
        return reportDAO.customSearch(userId, startDate, endDate, vendor);
    }
}
